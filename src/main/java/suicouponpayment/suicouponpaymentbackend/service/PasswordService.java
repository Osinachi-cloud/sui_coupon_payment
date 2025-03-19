package suicouponpayment.suicouponpaymentbackend.service;

import suicouponpayment.suicouponpaymentbackend.model.dto.request.ChangePasswordRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.OtpRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.PasswordResetRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.ApiResponse;
//import oai.commons.model.response.ApiResponse;

public interface PasswordService {
    ApiResponse<String> requestPasswordReset(String emailAddress);

    ApiResponse<String> resetPassword(PasswordResetRequest passwordResetRequest);

    ApiResponse<String> changePassword(ChangePasswordRequest changePasswordRequest);

    String encode(String password);

    void validateNewPassword(PasswordResetRequest passwordResetRequest);

    void validateNewPassword(ChangePasswordRequest passwordResetRequest);

    void validateNewPassword(String password);

    ApiResponse<String> validatePasswordResetCode(String emailAddress, OtpRequest passwordResetRequest);

    boolean passwordMatch(String rawPassword, String encrypted);
}
