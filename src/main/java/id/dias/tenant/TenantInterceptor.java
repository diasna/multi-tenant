/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.security.core.context.SecurityContextHolder;

import id.dias.tenant.user.User;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
public class TenantInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -9147671859760730323L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        return addTenantIdIfObjectIsTenantEntity(entity, state, propertyNames);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        return addTenantIdIfObjectIsTenantEntity(entity, currentState,
                propertyNames);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        addTenantIdIfObjectIsTenantEntity(entity, state, propertyNames);
    }

    private boolean addTenantIdIfObjectIsTenantEntity(Object entity,
            Object[] state, String[] propertyName) {
        if (entity instanceof TenantSupport) {
            for (int index = 0; index < propertyName.length; index++) {
                if (propertyName[index].equals("tenantId")) {
                    state[index] = ((User) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal()).getTenantId();
                    return true;
                }
            }
            throw new ClassCastException();
        }
        return false;
    }
}
