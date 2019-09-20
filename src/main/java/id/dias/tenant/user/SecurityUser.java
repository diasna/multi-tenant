/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author diasa
 * @version 1.0, Sep 17, 2019
 * @since
 */
public interface SecurityUser extends UserDetails {

    String getTenantId();

    String getUuid();

    default boolean isAccountNonExpired() {
        return false;
    }

    default boolean isAccountNonLocked() {
        return false;
    }

    default boolean isCredentialsNonExpired() {
        return false;
    }

    default boolean isEnabled() {
        return false;
    }
}
