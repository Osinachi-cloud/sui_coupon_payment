package suicouponpayment.suicouponpaymentbackend.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

@NoArgsConstructor
@ToString
public class ApiException {
    private String message;
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}