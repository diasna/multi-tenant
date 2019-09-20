/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Aspect
@Component
public class TenantServiceAspect {
    @Before("execution(* id.dias.tenant.TenantService+.*(..)) && @annotation(id.dias.tenant.ReadsTenantData) && target(tenantService)")
    public void before(JoinPoint joinPoint, TenantService tenantService) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String tenantId = null;
        String[] parameterNames = codeSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            if ("tenantId".equals(parameterNames[i])) {
                tenantId = (String) joinPoint.getArgs()[i];
            }
        }
        if (tenantId == null) {
            tenantId = (String) tenantService.getCurrentTenantIdentifer();
        }
        tenantService.getEntityManager()
                .unwrap(Session.class)
                .enableFilter("tenantFilter")
                .setParameter("tenantId", tenantId);
    }
}
