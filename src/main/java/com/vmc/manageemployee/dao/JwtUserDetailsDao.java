package com.vmc.manageemployee.dao;

import com.vmc.manageemployee.model.UserSystem;

public interface JwtUserDetailsDao {

    UserSystem findByUsername(String username);
}
