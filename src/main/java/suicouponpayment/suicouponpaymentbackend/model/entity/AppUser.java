package suicouponpayment.suicouponpaymentbackend.model.entity;

import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table(name = "immigration_user")
public class AppUser extends User {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
