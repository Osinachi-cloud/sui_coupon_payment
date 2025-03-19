package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import java.util.Objects;

public class ChangePasswordRequest {
    private String emailAddress;
    private String newPassword;
    private String confirmPassword;


    public ChangePasswordRequest(String emailAddress, String newPassword, String confirmPassword) {
        this.emailAddress = emailAddress;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public ChangePasswordRequest() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ChangePasswordRequest that = (ChangePasswordRequest) object;
        return Objects.equals(emailAddress, that.emailAddress) && Objects.equals(newPassword, that.newPassword) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, newPassword, confirmPassword);
    }
}
