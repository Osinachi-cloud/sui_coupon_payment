package suicouponpayment.suicouponpaymentbackend.utils;

import suicouponpayment.suicouponpaymentbackend.model.dto.response.PermissionDto;
import suicouponpayment.suicouponpaymentbackend.model.dto.response.RoleDto;
import suicouponpayment.suicouponpaymentbackend.model.entity.Permission;
import suicouponpayment.suicouponpaymentbackend.model.entity.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DtoMapper {

    public static Collection<PermissionDto> mapToCollectionOfPermissionDto(Collection<Permission> permissionCollection) {
        Collection<PermissionDto> permissionDtos = new ArrayList<>();
        for (Permission permission : permissionCollection) {
            if (permission != null) {
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setName(permission.getName());
                permissionDto.setId(permission.getId());
                permissionDto.setDescription(permission.getDescription());
                permissionDto.setCategory(permission.getCategory());
                permissionDtos.add(permissionDto);
            }
        }
        return permissionDtos;
    }

    public static RoleDto mapRoleToDto(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        roleDto.setDateCreated(role.getDateCreated().toString());
        roleDto.setLastUpdated(role.getLastUpdated().toString());
        roleDto.setPermissionNames(mapToCollectionNamesToString(role.getPermissions()));
        roleDto.setPermissionsDto(mapToCollectionOfPermissionDto(role.getPermissions()));
        return roleDto;
    }

    public static List<String> mapToCollectionNamesToString(Collection<Permission> permissionCollection) {
        List<String> permissionNames = new ArrayList<>();
        for (Permission permission : permissionCollection) {
            if (permission != null) {
                String permissionName = permission.getName();
                permissionNames.add(permissionName);
            }
        }
        return permissionNames;
    }
}
