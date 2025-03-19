package suicouponpayment.suicouponpaymentbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

import java.time.Instant;

@ToString
@MappedSuperclass
public abstract class User  extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "email_address", unique = true)
    protected String emailAddress;

    @Column(name = "phone_number", unique = true, nullable = false)
    protected String phoneNumber;

    @Column(name = "password")
    protected String password;

    @Column(name = "last_login")
    protected Instant lastLogin;

    @Column(name = "expired_password")
    protected boolean expiredPassword;

    @Column(name = "last_password_change")
    protected Instant lastPasswordChange;

    @Column(name = "is_enabled")
    protected boolean enabled = true;

    @Column(name = "login_attempts")
    protected int loginAttempts;

    @Column(name = "account_locked")
    protected boolean accountLocked;

    @Column(name = "nationality")
    protected String nationality;


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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isExpiredPassword() {
        return expiredPassword;
    }

    public void setExpiredPassword(boolean expiredPassword) {
        this.expiredPassword = expiredPassword;
    }

    public Instant getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Instant lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}