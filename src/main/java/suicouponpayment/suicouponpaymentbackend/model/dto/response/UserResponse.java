package suicouponpayment.suicouponpaymentbackend.model.dto.response;

import suicouponpayment.suicouponpaymentbackend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String email;
    private Role role;
}
