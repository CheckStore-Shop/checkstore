package io.checkstore.common;

public abstract class CommonConstants {


    public static abstract class EntityName {
        public static final String CUSTOMER = "customer";
        public static final String APP_USER = "app_users";
        public static final String ROLE = "roles";
        public static final String AUTHORITY = "authorities";
        public static final String USER_ROLE = "user_roles";
        public static final String ROLE_AUTHORITY = "role_authorities";
        public static final String GROUP = "groups";
        public static final String GROUP_MEMBER = "group_members";
        public static final String FEATURE = "features";
    }

    public static abstract class EntityStatus{
        public static final Integer LOCKED = 0;
        public static final Integer DISABLED = 0;
        public static final Integer UNVERIFIED = 1;

    }

    public static abstract class Role {
        public static final String DEFAULT_ROLE_CUSTOMER = "CUSTOMER";

        public static final String DEFAULT_ROLE_SELLER = "SELLER";
    }

    public static abstract class AccountAllocationCount {
        public static  final Long CUSTOMER_ACCOUNT_COUNT = 1L;
        public static final Long SELLER_ACCOUNT_COUNT = 1L;
    }

    public static abstract class FeatureType {

        public static final Integer APP = 2;
    }
}
