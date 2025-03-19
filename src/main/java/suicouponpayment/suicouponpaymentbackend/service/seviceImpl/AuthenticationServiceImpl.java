package suicouponpayment.suicouponpaymentbackend.service.seviceImpl;


import suicouponpayment.suicouponpaymentbackend.config.security.TokenUtils;
import suicouponpayment.suicouponpaymentbackend.exceptions.IosProException;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.AuthenticationRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.CustomUserDetails;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.LoginResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.Token;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;
import suicouponpayment.suicouponpaymentbackend.service.AuthenticationService;
import suicouponpayment.suicouponpaymentbackend.service.PasswordService;
import suicouponpayment.suicouponpaymentbackend.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;


    private final PasswordService passwordService;

    private final TokenUtils tokenUtils;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService, PasswordService passwordService, TokenUtils tokenUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordService = passwordService;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public LoginResponse authenticate(AuthenticationRequest authenticationRequest) {

        boolean passwordMatch = passwordService.passwordMatch("A$123456", "$2a$10$S2O5dHSh41MHL7KvAPThm.VudYs0dvo19oFVGnBgvTiXiAjBbAVrK");

        log.info("passwordMatch :{}", passwordMatch);
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));

            UserDto user = getUser(authentication);
            Token token = tokenUtils.generateAccessAndRefreshToken(user);

            LoginResponse loginResponse = new LoginResponse(user, token);

            onSuccessfulAuthentication(user);
            return loginResponse;
        } catch (BadCredentialsException e) {
            log.error("Bad login credentials: {}" , authenticationRequest.getEmailAddress());
            onFailedAuthentication(authenticationRequest.getEmailAddress(), e);
            throw new BadCredentialsException("Incorrect email address or password");
        } catch (AuthenticationException e) {
            log.error("Authentication error for user: {}", authenticationRequest.getEmailAddress());
            onFailedAuthentication(authenticationRequest.getEmailAddress(), e);
            if (e.getCause() != null) {
                Throwable cause = e.getCause();
                if (cause.getCause() != null) {
                    Throwable initialCause = cause.getCause();
                    throw new IosProException(initialCause.getMessage() != null ? initialCause.getMessage() : "Error processing request");
                }
                throw new IosProException(cause.getMessage() != null ? cause.getMessage() : "Error processing request");
            }
            throw new IosProException(e.getMessage() != null ? e.getMessage() : "Error processing request");
        }
    }

    @Override
    public Token refreshAccessToken(String refreshToken) {

        Claims claims = tokenUtils.validateRefreshToken(refreshToken);
        UserDto customer = userService.getUserDto(claims.getSubject());
        return tokenUtils.generateAccessAndRefreshToken(customer);
    }

    private UserDto getUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    private void onSuccessfulAuthentication(UserDto user) {
        log.info("Successful authentication for user: {}" , user.getEmailAddress());
        userService.updateLastLogin(user);
    }

    private void onFailedAuthentication(String emailAddress, Throwable e) {
        userService.updateLoginAttempts(emailAddress);
    }


    @Override
    public UserDto getAuthenticatedUser() {
        if (SecurityContextHolder.getContext() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return getUser(authentication);
            }
        }
        throw new AccessDeniedException("Unauthenticated user");
    }

    public String getAuthenticatedUserEmailAddress() {
        return getAuthenticatedUser().getEmailAddress();
    }


}

