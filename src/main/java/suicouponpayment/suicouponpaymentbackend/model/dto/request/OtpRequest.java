package suicouponpayment.suicouponpaymentbackend.model.dto.request;

import java.util.Objects;

public class OtpRequest {
        private String resetCode;

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    @Override
    public String toString() {
        return "OtpRequest{" +
                "resetCode='" + resetCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OtpRequest that = (OtpRequest) object;
        return Objects.equals(resetCode, that.resetCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resetCode);
    }
}
