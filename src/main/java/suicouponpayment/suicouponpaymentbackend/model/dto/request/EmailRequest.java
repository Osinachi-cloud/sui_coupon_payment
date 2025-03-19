package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import lombok.Data;

@Data
public class EmailRequest {
    private String body;
    private String to;
    private String subject;
}
