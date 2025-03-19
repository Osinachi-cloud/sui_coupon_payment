package suicouponpayment.suicouponpaymentbackend.config.security;

import suicouponpayment.suicouponpaymentbackend.exceptions.IosProException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    public TokenAuthenticationFilter(TokenAuthenticationProvider tokenAuthenticationProvider) {
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        } else {

            String token = header.substring(7);

            try {
                Authentication authentication = tokenAuthenticationProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.trace("User token is valid and is authenticated");

            } catch (AuthenticationException ex) {
                logger.error("Token authentication error: " + ex.getMessage());
                SecurityContextHolder.clearContext();
                response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
                throw new IosProException("Token authentication error: " + ex.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}

