package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import lombok.ToString;


public class PasswordResetRequest {

    private String emailAddress;
    private String resetCode;
    private String newPassword;
    private String confirmPassword;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public PasswordResetRequest(String emailAddress, String resetCode, String newPassword, String confirmPassword) {
        this.emailAddress = emailAddress;
        this.resetCode = resetCode;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public PasswordResetRequest() {
    }

    @Override
    public String toString() {
        return "PasswordResetRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", resetCode='" + resetCode + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
