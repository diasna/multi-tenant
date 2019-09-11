/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import id.dias.tenant.user.User;

/**
 * @author diasa
 * @version 1.0, Jan 10, 2019
 * @since
 */
public class TokenAuthenticationConverter
        extends DefaultUserAuthenticationConverter {
    private static final String TENANTID = "tenant_id";
    private static final String USERID = "user_id";
    private static final String USERNAME = "user_name";

    @Override
    public Map<String, ?> convertUserAuthentication(
            Authentication authentication) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>(
                super.convertUserAuthentication(authentication));
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            response.put(TENANTID, user.getTenantId());
            response.put(USERID, user.getUuid());
            response.put(USERNAME, user.getUsername());
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication authentication = super.extractAuthentication(map);
        if (map.containsKey(USERID) && map.containsKey(USERNAME)
                && map.containsKey(TENANTID)) {
            User user = new User();
            user.setUuid((String) map.get(USERID));
            user.setUsername((String) map.get(USERNAME));
            user.setTenantId((String) map.get(TENANTID));
            return new UsernamePasswordAuthenticationToken(user, "N/A",
                    authentication.getAuthorities());
        }
        throw new BadCredentialsException("Invalid token");
    }
}