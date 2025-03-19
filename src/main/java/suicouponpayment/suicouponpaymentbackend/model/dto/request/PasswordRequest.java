package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import java.util.Objects;

public class PasswordRequest {

    private String newPassword;
    private String confirmPassword;

    public PasswordRequest(String newPassword, String confirmPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public PasswordRequest() {
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
        PasswordRequest that = (PasswordRequest) object;
        return Objects.equals(newPassword, that.newPassword) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newPassword, confirmPassword);
    }
}
