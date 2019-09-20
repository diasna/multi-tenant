/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import id.dias.tenant.user.User;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Service
public abstract class TenantService {
    @PersistenceContext
    private EntityManager entityManager;

    Serializable getCurrentTenantIdentifer() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getTenantId();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
