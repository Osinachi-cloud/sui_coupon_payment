package suicouponpayment.suicouponpaymentbackend.controller;


import suicouponpayment.suicouponpaymentbackend.model.dto.request.*;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.ApiResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.LoginResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.RegistrationResponse;
import suicouponpayment.suicouponpaymentbackend.service.AuthenticationService;
import suicouponpayment.suicouponpaymentbackend.service.PasswordService;
import suicouponpayment.suicouponpaymentbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final PasswordService passwordService;

    private final AuthenticationService authenticationService;

    public AuthController(UserService userService, PasswordService passwordService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.passwordService = passwordService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody AuthenticationRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        log.info("request login: {}", request);
        LoginResponse authenticationResponse = authenticationService.authenticate(request);

        ApiResponse<LoginResponse> apiResponse = ApiResponse.<LoginResponse>builder()
                .data(authenticationResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, RegistrationResponse>>> register(@RequestBody RegisterRequest request) {
        RegistrationResponse registrationResponse =
                userService.register(request);

        Map<String, RegistrationResponse> regRes = new HashMap<>();
        regRes.put("data", registrationResponse);

        ApiResponse<Map<String, RegistrationResponse>> mapApiResponse = ApiResponse.<Map<String, RegistrationResponse>>builder()
                .data(regRes)
                .build();
        return ResponseEntity.ok(mapApiResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> requestPasswordReset(@RequestBody EmailAddressRequest request) {
        return ResponseEntity.ok(passwordService.requestPasswordReset(request.getEmailAddress()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestParam String emailAddress, @RequestParam String resetCode, @RequestBody PasswordRequest request) {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest(emailAddress, resetCode, request.getNewPassword(), request.getConfirmPassword());
        return ResponseEntity.ok(passwordService.resetPassword(passwordResetRequest));
    }

    @PostMapping("validate-reset-code")
    public ResponseEntity<ApiResponse<String>> validatePasswordResetCode(@RequestParam String emailAddress, @RequestBody OtpRequest otpRequest) {
        return ResponseEntity.ok(passwordService.validatePasswordResetCode(emailAddress, otpRequest));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestParam String emailAddress, @RequestBody PasswordRequest request) {
        ChangePasswordRequest passwordRequest = new ChangePasswordRequest(emailAddress, request.getNewPassword(), request.getConfirmPassword());
        return ResponseEntity.ok(passwordService.changePassword(passwordRequest));
    }
}
