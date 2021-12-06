package com.vmc.manageemployee.repository;


import com.vmc.manageemployee.model.UserSystem;

public interface JwtUserDetailsRepository {

    UserSystem findByUsername(String username);
}
