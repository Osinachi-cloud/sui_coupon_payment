package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
//    @NotNull(message = "Email is required")
//    @Size(min = 4, max = 20)
    private String emailAddress;

//    @NotNull
//    @Size(min = 4, max = 20)
    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AuthenticationRequest that = (AuthenticationRequest) object;
        return Objects.equals(emailAddress, that.emailAddress) && Objects.equals(password, that.password);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "email='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, password);
    }
}
