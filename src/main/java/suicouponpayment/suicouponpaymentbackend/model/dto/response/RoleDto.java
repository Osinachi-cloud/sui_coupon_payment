package suicouponpayment.suicouponpaymentbackend.model.dto.response;


import suicouponpayment.suicouponpaymentbackend.model.entity.Role;
import suicouponpayment.suicouponpaymentbackend.utils.DtoMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
public class RoleDto {

    private String name;
//    private RoleName name;

    private String description;

    private String dateCreated;

    private String lastUpdated;

    private List<String> permissionNames = new ArrayList<>();

    private Collection<PermissionDto> permissionsDto;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.name = role.getName();
        this.description = role.getDescription();
        this.dateCreated = role.getDateCreated().toString();
        this.lastUpdated = role.getLastUpdated().toString();
        this.permissionsDto = DtoMapper.mapToCollectionOfPermissionDto(role.getPermissions());
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<String> getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(List<String> permissionNames) {
        this.permissionNames = permissionNames;
    }

    public Collection<PermissionDto> getPermissionsDto() {
        return permissionsDto;
    }

    public void setPermissionsDto(Collection<PermissionDto> permissionsDto) {
        this.permissionsDto = permissionsDto;
    }
}