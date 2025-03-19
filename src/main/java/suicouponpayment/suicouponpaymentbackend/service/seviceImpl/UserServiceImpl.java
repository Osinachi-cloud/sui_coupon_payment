package suicouponpayment.suicouponpaymentbackend.service.seviceImpl;


import suicouponpayment.suicouponpaymentbackend.exceptions.IosProException;
import suicouponpayment.suicouponpaymentbackend.model.dto.request.RegisterRequest;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.RegistrationResponse;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.UserDto;
import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;
import suicouponpayment.suicouponpaymentbackend.model.entity.Role;
import suicouponpayment.suicouponpaymentbackend.repository.RoleRepository;
import suicouponpayment.suicouponpaymentbackend.repository.UserRepository;
import suicouponpayment.suicouponpaymentbackend.service.PasswordService;
import suicouponpayment.suicouponpaymentbackend.service.UserService;
import suicouponpayment.suicouponpaymentbackend.utils.UserValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordService passwordService;



    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordService passwordService){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
    }


    public RegistrationResponse register(RegisterRequest request) {

        validate(request);
        log.info("request user : {}" , request);
        Optional<AppUser> userDetails = userRepository.findByEmailAddress(request.getEmailAddress());
        if(userDetails.isPresent()){
            throw new IosProException("User already exists");
        }

        Role role = new Role();
        role.setName("DEFAULT");
        role.setDescription("Default user");

        roleRepository.save(role);

        AppUser user = new AppUser();
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(passwordService.encode(request.getPassword()));
        user.setRole(role);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLoginAttempts(0);

        AppUser savedUser = userRepository.save(user);

        log.info("saved User {}", savedUser);
        return new RegistrationResponse("User Successfully registered");
    }

    @Override
    public void updateLastLogin(UserDto userDto) {
        AppUser user = getUser(userDto.getEmailAddress());
        user.setLastLogin(Instant.now());
        user.setLoginAttempts(0);
        userRepository.save(user);
        log.info("Updated customer [{}] last login", user.getEmailAddress());
    }

    @Override
    public AppUser getUser(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new IosProException(String.format("emailAddress [%s] not found", emailAddress)));
    }

    @Override
    public void updateLoginAttempts(String emailAddress) {
        Optional<AppUser> optionalCustomer = userRepository.findByEmailAddress(emailAddress);
        if (optionalCustomer.isEmpty()) {
            log.warn("Customer with email address [{}] does not exist", emailAddress);
            return;
        }
        AppUser user = optionalCustomer.get();
        int loginAttempts = user.getLoginAttempts() + 1;
        user.setLoginAttempts(loginAttempts);
        userRepository.saveAndFlush(user);
        log.info("Updated user [{}] login attempts to {}", user.getEmailAddress(), loginAttempts);

    }

    @Override
    public UserDto getUserDto(String email) {

        log.debug("Getting customer with ID: {}", email);

        AppUser user = getUser(email);

        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmailAddress(user.getEmailAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEnabled(user.isEnabled());
        userDto.setPassword(user.getPassword());
        return userDto;
    }



    private void validate(RegisterRequest request) {

        if (StringUtils.isBlank(request.getFirstName()) ||
                StringUtils.isBlank(request.getLastName()) ||
                StringUtils.isBlank(request.getEmailAddress()) ||
                StringUtils.isBlank(request.getPhoneNumber()) ||
                StringUtils.isBlank(request.getPassword())) {
            throw new IosProException("Empty field values");
        }

        if (!UserValidationUtils.isValidEmail(request.getEmailAddress())) {
            throw new IosProException("Invalid email Address");
        }

        if (!UserValidationUtils.isValidPhoneNumber(request.getPhoneNumber())) {
            throw new IosProException("Invalid Phone number");
        }
        Optional<AppUser> existingUser = userRepository.findByEmailAddress(request.getEmailAddress());


        if (existingUser.isPresent()) {
            throw new IosProException("User Exists");
        }

        existingUser = userRepository.findByPhoneNumber(request.getPhoneNumber());

        if (existingUser.isPresent()) {
            throw new IosProException("Phone number exists");
        }
        passwordService.validateNewPassword(request.getPassword());
    }

}
