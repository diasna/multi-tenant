/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.springframework.security.core.GrantedAuthority;

import id.dias.tenant.TenantSupport;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Entity
@Table(name = "user")
@FilterDef(name = "tenantFilter",
        parameters = { @ParamDef(name = "tenantId", type = "string") })
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class User implements TenantSupport, SecurityUser, Serializable {

    private static final long serialVersionUID = 8558517047510959941L;

    @Column(name = "tenant_id")
    private String tenantId;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User [tenantId=" + tenantId + ", uuid=" + uuid + ", username="
                + username + "]";
    }

}
