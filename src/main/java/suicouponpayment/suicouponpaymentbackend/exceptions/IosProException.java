package suicouponpayment.suicouponpaymentbackend.exceptions;

import org.springframework.http.HttpStatus;

public class IosProException extends RuntimeException {
    private HttpStatus httpStatus;
    public IosProException(String message){
        super(message);
    }
    public IosProException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public IosProException(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
