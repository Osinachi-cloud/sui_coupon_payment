package suicouponpayment.suicouponpaymentbackend.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Table(name = "permission")
public class Permission extends BaseEntity {

    @Column(name = "name", unique=true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;

    public Permission(String name, String description, String category){
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}


