package suicouponpayment.suicouponpaymentbackend.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@JsonInclude(Include.NON_NULL)
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPage;

}
