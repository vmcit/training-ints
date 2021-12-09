package com.vmc.manageemployee.repositories;


import com.vmc.manageemployee.models.UserSystem;

public interface JwtUserDetailsRepository {

    UserSystem findByUsername(String username);
}
