package suicouponpayment.suicouponpaymentbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("role")
    private String role;

    private boolean isAuthenticated;

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

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AuthenticationResponse that = (AuthenticationResponse) object;
        return isAuthenticated == that.isAuthenticated && Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken, role, isAuthenticated);
    }
}
