package suicouponpayment.suicouponpaymentbackend.service;

import suicouponpayment.suicouponpaymentbackend.model.dto.request.RegisterRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.RegistrationResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;
import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;

public interface UserService {
    RegistrationResponse register(RegisterRequest request);

    void updateLastLogin(UserDto user);

    AppUser getUser(String emailAddress);

    void updateLoginAttempts(String emailAddress);

    UserDto getUserDto(String subject);
}
