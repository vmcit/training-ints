package com.vmc.manageemployee.service;

import java.util.ArrayList;

import com.vmc.manageemployee.model.UserSystem;
import com.vmc.manageemployee.repository.JwtUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUserDetailsRepository jwtUserDetailsRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            UserSystem user = new UserSystem();
            user = jwtUserDetailsRepository.findByUsername(username);
//            return new User("admin", "$2a$10$HeAsLAsudoGguPoqfzsemONLq4lyKTqHAzEN9Ynvv4Ol2R50UypYy",
//                    new ArrayList<>());
            return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
