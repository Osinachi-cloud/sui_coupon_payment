package suicouponpayment.suicouponpaymentbackend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationResponse {
    @JsonProperty("response")
    private String response;

    public RegistrationResponse(String response) {
        this.response = response;
    }
}

