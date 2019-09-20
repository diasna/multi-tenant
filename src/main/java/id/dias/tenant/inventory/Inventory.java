/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.inventory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;

import id.dias.tenant.TenantSupport;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Entity
@Table(name = "inventory")
@FilterDef(name = "tenantFilter",
        parameters = { @ParamDef(name = "tenantId", type = "string") })
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Inventory implements TenantSupport, Serializable {

    private static final long serialVersionUID = -6602497631361693980L;

    @Column(name = "tenant_id")
    private String tenantId;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @Column(name = "name")
    private String name;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
