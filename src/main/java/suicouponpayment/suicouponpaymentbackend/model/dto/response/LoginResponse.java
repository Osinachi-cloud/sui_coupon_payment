package suicouponpayment.suicouponpaymentbackend.model.dto.response;

import java.util.Objects;


public class LoginResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(UserDto customer, Token token) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmailAddress();
        this.role = customer.getRole().getName();
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }

    public LoginResponse() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LoginResponse that = (LoginResponse) object;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(role, that.role) && Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, role, accessToken, refreshToken);
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
