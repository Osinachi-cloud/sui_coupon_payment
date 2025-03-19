package suicouponpayment.suicouponpaymentbackend.model.dto.response;

import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;
import suicouponpayment.suicouponpaymentbackend.utils.DtoMapper;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private boolean enabled;
    private boolean accountLocked;
    private RoleDto role;
    private String phoneNumber;

    public UserDto(AppUser user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.role = DtoMapper.mapRoleToDto(user.getRole());

    }

    public UserDto(String password, String firstName, String lastName, String emailAddress, boolean enabled, boolean accountLocked, RoleDto role, String phoneNumber) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public UserDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}