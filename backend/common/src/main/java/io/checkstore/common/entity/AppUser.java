package io.checkstore.common.entity;

import io.checkstore.common.CommonConstants;
import io.checkstore.common.security.SecuredUser;
import io.checkstore.common.utils.CheckSoreHashSet;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import one.util.streamex.StreamEx;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Table(value = CommonConstants.EntityName.APP_USER)

public class AppUser extends AbstractJdbcEntity<Long> implements SecuredUser {

    private String username;
    private String preferredUserName;
    private String password;
    private String email;
    private Integer emailVerified;
    private String name;
    private String unsigned_name;
    private String phoneNumber;
    private Integer phoneNumberVerified;
    private Integer gender;
    private LocalDate birthdate;
    private Integer enabled;
    private Integer locked;

    @Transient
    private Set<Role> roles = new HashSet<>();

    @MappedCollection(idColumn = "user_id")
    private Set<Customer> customer = new CheckSoreHashSet<>();
    @MappedCollection(idColumn = "user_id")
    private Set<Seller> seller = new CheckSoreHashSet<>();

    @Transient
    private Set<Group> groups = new HashSet<>();
    
    @MappedCollection(idColumn ="user_id")
    private Set<UserRoleRef> roleRefs = new HashSet<>();

    @MappedCollection(idColumn = "user_id")
    private Set<UserGroupRef> groupRefs = new HashSet<>();

    @Transient
    private Set<String> authorities = new HashSet<>();



    public AppUser(Long id, String username, String preferredUserName, String password, String email,
                   Integer emailVerified, String name, String unsigned_name, String phoneNumber, Integer phoneNumberVerified,
                   Integer gender, LocalDate birthdate, Integer enabled, Integer locked, CheckSoreHashSet<Customer> customer, CheckSoreHashSet<Seller> seller,
                   Set<Role> roles, Set<Group> groups, Set<UserRoleRef> roleRefs, Set<UserGroupRef> groupRefs, Set<String> authorities){
        this.id = id;
        this.username = username;
        this.preferredUserName = preferredUserName;
        this.password = password;
        this.email = email;
        this.emailVerified = Objects.requireNonNullElse(emailVerified, CommonConstants.EntityStatus.UNVERIFIED);
        this.name = name;
        this.unsigned_name = unsigned_name;
        this.phoneNumber = phoneNumber;
        this.phoneNumberVerified = Objects.requireNonNullElse(phoneNumberVerified, CommonConstants.EntityStatus.UNVERIFIED);
        this.gender = gender;
        this.birthdate = birthdate;
        this.enabled = Objects.requireNonNullElse(enabled, CommonConstants.EntityStatus.DISABLED);
        this.locked = Objects.requireNonNullElse(locked, CommonConstants.EntityStatus.LOCKED);
        this.roles = Objects.requireNonNullElse(roles, new HashSet<>());
        this.groups = Objects.requireNonNullElse(groups, new HashSet<>());
        this.roleRefs = Objects.requireNonNullElse(roleRefs, fromRoles(roles));
        this.groupRefs = Objects.requireNonNullElse(groupRefs, fromGroups(groups));
        this.authorities = Objects.requireNonNullElse(authorities, new HashSet<>());
        this.customer = Objects.requireNonNullElse(customer, new CheckSoreHashSet<>(fromProfileType(roles)));
        this.seller = Objects.requireNonNullElse(seller, new CheckSoreHashSet<>(fromProfileType(roles)));
    }

    private void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    private Set<UserRoleRef> fromRoles(Set<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) return new HashSet<>();
        return StreamEx.of(roles)
                .map(Role::getId)
                .map(UserRoleRef::new)
                .toSet();
    }

    private Set<UserGroupRef> fromGroups(Set<Group> groups) {
        if (CollectionUtils.isEmpty(groups)) return new HashSet<>();
        return StreamEx.of(groups)
                .map(Group::getId)
                .map(UserGroupRef::new)
                .toSet();
    }

    private long fromProfileType(Set<Role> roles) {
        long limit = 0L;
        for (Role role :  roles){
            String roleName = role.getName();
            switch (roleName) {
                case CommonConstants.Role.DEFAULT_ROLE_CUSTOMER:
                    limit = CommonConstants.AccountAllocationCount.CUSTOMER_ACCOUNT_COUNT;
                    break;
                case CommonConstants.Role.DEFAULT_ROLE_SELLER:
                    limit = CommonConstants.AccountAllocationCount.SELLER_ACCOUNT_COUNT;
                default:
                    limit =0L;
                    break;
            }
        }
        return limit;
    }

    private long fromProfileType() {
        return 0L;
    }

    public Set<Long> roleIds(){
        if (CollectionUtils.isEmpty(roleRefs)) return new HashSet<>();
        return StreamEx.of(roleRefs)
                .map(UserRoleRef::getRoleId)
                .toImmutableSet();
    }

    public void addNewRole(Role role){
        Set<Role> roles = getRoles();
        Set<UserRoleRef> roleRefs = getRoleRefs();
        roles.add(role);
        roleRefs.add(new UserRoleRef(role.getId()));
        setRoles(roles);
        setRoleRefs(roleRefs);
    }

    public Set<Long> groupIds() {
        if (CollectionUtils.isEmpty(groupRefs)) return new HashSet<>();
        return StreamEx.of(groupRefs)
                .map(UserGroupRef::getGroupId)
                .toImmutableSet();
    }


    public Set<String> getAuthorityNames(){
        if (CollectionUtils.isEmpty(roles)) return new HashSet<>();
        return StreamEx.of(roles)
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .map(Authority::getName)
                .toImmutableSet();
    }


}
