package suicouponpayment.suicouponpaymentbackend.model.enums;


public enum Permission {

    IMMIGRATION_ADMIN("admin"),
    IMMIGRATION_USER("user");

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    private final String permission;
}

