package suicouponpayment.suicouponpaymentbackend.repository;


import suicouponpayment.suicouponpaymentbackend.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmailAddress(String emailAddress);

    Optional<AppUser> findByPhoneNumber(String phoneNumber);
}
