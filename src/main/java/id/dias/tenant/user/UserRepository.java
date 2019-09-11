/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
