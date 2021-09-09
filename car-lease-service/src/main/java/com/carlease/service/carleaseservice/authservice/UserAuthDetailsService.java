package com.carlease.service.carleaseservice.authservice;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.carlease.service.carleaseservice.bean.UserCredentials;
import com.carlease.service.carleaseservice.model.UserPrincipal;
import com.carlease.service.carleaseservice.repository.UserRepository;
/**
 * User Authentocation Service
 * @author Shruthi
 *
 */
@Component
public class UserAuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
        UserCredentials user = userRepository
                .findById(s)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + s + "Not Found in DB"));
        return UserPrincipal.create(user);

    }
}
