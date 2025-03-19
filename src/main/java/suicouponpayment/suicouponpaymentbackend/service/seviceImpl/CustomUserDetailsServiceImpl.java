package suicouponpayment.suicouponpaymentbackend.service.seviceImpl;


import suicouponpayment.suicouponpaymentbackend.model.dto.response.CustomUserDetails;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.PermissionDto;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;
import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;
import suicouponpayment.suicouponpaymentbackend.repository.UserRepository;
import suicouponpayment.suicouponpaymentbackend.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;



@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByEmailAddress(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("customer not found for email address=%s", username));
        }

        UserDto userDto = new UserDto(user.get());
        userDto.setPassword(user.get().getPassword());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if(userDto.getRole().getPermissionsDto() != null){
            for(PermissionDto permissionDto: userDto.getRole().getPermissionsDto()){
                grantedAuthorities.add(new SimpleGrantedAuthority(permissionDto.getName()));
            }
        }

        log.info("grantedAuthorities : " + grantedAuthorities);

        return new CustomUserDetails(userDto, grantedAuthorities);
    }
}
