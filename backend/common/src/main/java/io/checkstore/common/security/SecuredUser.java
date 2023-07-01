package io.checkstore.common.security;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface SecuredUser extends Serializable {

    Long getId();
    String getUsername();
    String getPreferredUserName();
    String getPassword();
    String getName();
    String getPhoneNumber();
    Integer getGender();
    LocalDate getBirthdate();
    Integer getEnabled();
    Integer getLocked();
    Set<String> getAuthorityNames();
}
