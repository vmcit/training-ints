package com.vmc.manageemployee.dao;

import com.vmc.manageemployee.models.UserSystem;

public interface JwtUserDetailsDao {

    UserSystem findByUsername(String username);
}
