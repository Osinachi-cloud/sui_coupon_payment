package suicouponpayment.suicouponpaymentbackend.service.seviceImpl;


import suicouponpayment.suicouponpaymentbackend.exceptions.IosProException;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.ChangePasswordRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.EmailRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.OtpRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.PasswordResetRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.ApiResponse;
import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;
import suicouponpayment.suicouponpaymentbackend.model.entity.PasswordReset;
import suicouponpayment.suicouponpaymentbackend.repository.PasswordResetRepository;
import suicouponpayment.suicouponpaymentbackend.repository.UserRepository;
import suicouponpayment.suicouponpaymentbackend.service.NotificationService;
import suicouponpayment.suicouponpaymentbackend.service.PasswordService;
import suicouponpayment.suicouponpaymentbackend.utils.NumberUtils;
import suicouponpayment.suicouponpaymentbackend.utils.UserValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;

    private final NotificationService notificationService;


    public PasswordServiceImpl(
            UserRepository userRepository,
            PasswordResetRepository passwordResetRepository,
            PasswordEncoder passwordEncoder,
            NotificationService notificationService) {
        this.userRepository = userRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationService = notificationService;
    }


    @Override
    public ApiResponse<String> requestPasswordReset(String emailAddress){

        log.info("Request for password reset from customer with email address [{}]" , emailAddress);

        if (!UserValidationUtils.isValidEmail(emailAddress)) {
            log.info("Customer email address [{}] invalid for password reset"  , emailAddress);
            throw new IosProException("Invalid email Address");
        }

        Optional<AppUser> optionalUser = userRepository.findByEmailAddress(emailAddress);

        if (optionalUser.isEmpty()) {
            log.info("A customer with email address [{}] not found for password reset" , emailAddress);
            throw new IosProException("User not found");
        }
        final AppUser user = optionalUser.get();

        try {
            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setEmailAddress(emailAddress);

            String resetCode = NumberUtils.generate(6);

            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setBody(resetCode);
            emailRequest.setTo(emailAddress);
            emailRequest.setSubject("Email Reset");

            notificationService.sendEmailRequest(emailRequest);

            passwordReset.setResetCode(resetCode);
            passwordReset.setGeneratedOn(Instant.now());

            passwordReset.setExpiredOn(Instant.now().plus(5, ChronoUnit.MINUTES));
            passwordResetRepository.save(passwordReset);

            log.info("user [{}] has requested a password reset process and reset code sent to email [{}]" , user.getEmailAddress() , user.getRole());
            return ApiResponse.<String>builder().data("Password reset code sent to email : " + resetCode).build();

        } catch (IosProException e) {
            log.info("Failed to request password reset for email address [{}]", emailAddress);
            throw new IosProException("Error : " + e.getMessage());
        }
    }

    @Override
    public ApiResponse<String> resetPassword(PasswordResetRequest passwordResetRequest) {

        log.info("Resetting password for customer with email address {} " , passwordResetRequest.getEmailAddress());

        final PasswordReset passwordReset = passwordResetRepository.findFirstByEmailAddressOrderByDateCreatedDesc(passwordResetRequest.getEmailAddress());

        if (passwordReset == null) {
            log.info("Email address [{}] not found for password reset" , passwordResetRequest.getEmailAddress());
            throw new IosProException("email address not found");

        }

        log.info("Found password reset : {}" , passwordReset);

        if (!passwordReset.isVerified()){
            throw new IosProException("Password reset code unverified");
        }

        if (!passwordReset.getResetCode().equals(passwordResetRequest.getResetCode())) {
            log.info("Invalid password reset code [{}] for email address {} " , passwordResetRequest.getResetCode() , passwordResetRequest.getEmailAddress());
            throw new IosProException("Invalid reset code");
        }

        if (Instant.now().isAfter(passwordReset.getExpiredOn())) {
            log.info("Password reset code [{}] has expired" , passwordResetRequest.getResetCode());
            throw new IosProException("Password reset code expired");
        }

        validateNewPassword(passwordResetRequest);

        try {

            AppUser user = userRepository.findByEmailAddress(passwordReset.getEmailAddress())
                    .orElseThrow(() -> new IosProException("User not found"));

            user.setPassword(encode(passwordResetRequest.getNewPassword()));
            user.setLastPasswordChange(Instant.now());
            userRepository.save(user);

            passwordReset.setVerified(true);
            passwordResetRepository.save(passwordReset);

//            notificationService.resetPasswordSuccess(new String[]{customer.getEmailAddress()}, customer.getFirstName());

            log.info("user [{}] password reset successfully" , user.getEmailAddress());
            return ApiResponse.<String>builder()
                    .data("Successfully Created")
                    .build();

        } catch (Exception e) {
            log.info("Failed to reset password for customer with email address [{}]" , passwordResetRequest.getEmailAddress());
            throw new IosProException("Password reset code expired");

        }
    }

    @Override
    public ApiResponse<String> changePassword(ChangePasswordRequest changePasswordRequest) {

        log.info("Resetting password for customer with email address {} " , changePasswordRequest.getEmailAddress());

        PasswordReset passwordReset = new PasswordReset();

        validateNewPassword(changePasswordRequest);

        try {

            AppUser user = userRepository.findByEmailAddress(passwordReset.getEmailAddress())
                    .orElseThrow(() -> new IosProException("User not found"));

            user.setPassword(encode(changePasswordRequest.getNewPassword()));
            user.setLastPasswordChange(Instant.now());
            userRepository.save(user);

            passwordReset.setVerified(true);
            passwordResetRepository.save(passwordReset);

//            notificationService.resetPasswordSuccess(new String[]{customer.getEmailAddress()}, customer.getFirstName());

            log.info("user [{}] password reset successfully" , user.getEmailAddress());
            return ApiResponse.<String>builder()
                    .data("Successfully Created")
                    .build();

        } catch (Exception e) {
            log.info("Failed to reset password for customer with email address [{}]" , changePasswordRequest.getEmailAddress());
            throw new IosProException("Password reset code expired");

        }
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void validateNewPassword(PasswordResetRequest passwordResetRequest) {
        log.info("passwordResetRequest :{}", passwordResetRequest);

        if (StringUtils.isBlank(passwordResetRequest.getNewPassword()) || StringUtils.isBlank(passwordResetRequest.getConfirmPassword())) {
            throw new IosProException("Password Empty");
        }
        List<String> passwordErrors = UserValidationUtils.getPasswordErrors(passwordResetRequest.getNewPassword());

        if (!passwordErrors.isEmpty()) {
            throw new IosProException(passwordErrors.toString());
        }

        if (!passwordResetRequest.getNewPassword().equals(passwordResetRequest.getConfirmPassword())) {
            throw new IosProException("Password Mismatch");
        }
    }

    @Override
    public void validateNewPassword(ChangePasswordRequest passwordResetRequest) {

        if (StringUtils.isBlank(passwordResetRequest.getNewPassword()) || StringUtils.isBlank(passwordResetRequest.getConfirmPassword())) {
            throw new IosProException("Password Empty");
        }
        List<String> passwordErrors = UserValidationUtils.getPasswordErrors(passwordResetRequest.getNewPassword());

        if (!passwordErrors.isEmpty()) {
            throw new IosProException(passwordErrors.toString());
        }

        if (!passwordResetRequest.getNewPassword().equals(passwordResetRequest.getConfirmPassword())) {
            throw new IosProException("Password Mismatch");
        }
    }

    @Override
    public void validateNewPassword(String password) {

        if (StringUtils.isBlank(password)) {
            throw new IosProException("Password Empty");
        }
        List<String> passwordErrors = UserValidationUtils.getPasswordErrors(password);

        if (!passwordErrors.isEmpty()) {
            throw new IosProException(passwordErrors.toString());
        }
    }

    @Override
    public ApiResponse<String> validatePasswordResetCode(String emailAddress, OtpRequest request) {

        log.info("Validating password reset code for customer with email address {}", emailAddress);

        final PasswordReset passwordReset = passwordResetRepository.findFirstByEmailAddressOrderByDateCreatedDesc(emailAddress);

        if (passwordReset == null) {
            log.info("Email address [{}] not found for password reset" , emailAddress);
            throw new IosProException("Email Address not found");
        }

        log.info("Found password reset : {}" , passwordReset);

        if (!passwordReset.getResetCode().equals(request.getResetCode())) {
            log.info("Invalid password reset code [{}] for email address [{}]" , request.getResetCode() , emailAddress);
            throw new IosProException("Invalid reset code");
        }

        if (Instant.now().isAfter(passwordReset.getExpiredOn())) {
            log.info("Password reset code [{}] has expired" , request.getResetCode());
            throw new IosProException("Password reset code expired");
        }

        try {
            passwordReset.setVerified(true);
            passwordResetRepository.save(passwordReset);

            log.info("Password reset code [{}] successfully verified :" , passwordReset.getEmailAddress());
            return ApiResponse.<String>builder().data("Successful").build();

        } catch (Exception e) {
            log.info("Failed to validate password reset code sent to email [{}]" , passwordReset.getEmailAddress());
            throw new IosProException("Error processing request");
        }
    }

    @Override
    public boolean passwordMatch(String rawPassword, String encrypted) {
        return passwordEncoder.matches(rawPassword, encrypted);
    }
}
