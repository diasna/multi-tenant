/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author diasa
 * @version 1.0, Sep 17, 2019
 * @since
 */
public class DefaultSecurityUser implements SecurityUser {

    private static final long serialVersionUID = -3961903534835586404L;

    private final String tenantId;
    private final String uuid;
    private final String username;
    private final String password;

    public DefaultSecurityUser(DefaultSecurityUser.Builder builder) {
        this.tenantId = builder.tenantId;
        this.uuid = builder.uuid;
        this.username = builder.username;
        this.password = builder.password;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public static class Builder {
        String tenantId;
        String uuid;
        String username;
        String password;

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public DefaultSecurityUser build() {
            return new DefaultSecurityUser(this);
        }
    }
}
