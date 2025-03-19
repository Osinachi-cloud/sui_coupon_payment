package suicouponpayment.suicouponpaymentbackend.model.dto.response;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private UserDto user;

    private Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(UserDto user, Set<GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

//        return !user.isAccountLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

//        return user.isEnabled();
        return true;
    }


    public UserDto getUser() {
        return user;
    }

}
