package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import java.util.Objects;

public class EmailAddressRequest {

    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "EmailAddressRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmailAddressRequest that = (EmailAddressRequest) object;
        return Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }
}
