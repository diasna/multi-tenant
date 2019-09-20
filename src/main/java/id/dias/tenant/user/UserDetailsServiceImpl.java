/* Copyright (C) 2019 ASYX International B.V. All rights reserved. */
package id.dias.tenant.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author diasa
 * @version 1.0, Sep 11, 2019
 * @since
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SecurityUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
