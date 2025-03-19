package suicouponpayment.suicouponpaymentbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Table(name = "password_reset")
public class PasswordReset extends BaseEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "is_verified")
    private boolean verified;

    @Column(name = "reset_code")
    private String resetCode;

    @Column(name = "generated_on")
    private Instant generatedOn;

    @Column(name = "expired_on")
    private Instant expiredOn;

    @Column(name = "device_id")
    private String deviceId;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public Instant getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Instant generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Instant getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Instant expiredOn) {
        this.expiredOn = expiredOn;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
