package suicouponpayment.suicouponpaymentbackend.service;

import suicouponpayment.suicouponpaymentbackend.model.dto.request.AuthenticationRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.LoginResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.Token;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;

public interface AuthenticationService {

    LoginResponse authenticate(AuthenticationRequest authenticationRequest);
    Token refreshAccessToken(String token);

    UserDto getAuthenticatedUser();
}
