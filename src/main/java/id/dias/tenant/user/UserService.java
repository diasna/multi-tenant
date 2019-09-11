/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
public interface UserService {
    User findByUsername(String username);
}
