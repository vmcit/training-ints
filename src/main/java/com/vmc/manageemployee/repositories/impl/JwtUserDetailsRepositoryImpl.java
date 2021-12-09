package com.vmc.manageemployee.repositories.impl;

import com.vmc.manageemployee.dao.JwtUserDetailsDao;
import com.vmc.manageemployee.models.UserSystem;
import com.vmc.manageemployee.repositories.JwtUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JwtUserDetailsRepositoryImpl implements JwtUserDetailsRepository {
    @Autowired
    JwtUserDetailsDao jwtUserDetailsDao;
    @Override
    public UserSystem findByUsername(String username) {
        return jwtUserDetailsDao.findByUsername(username);
    }
}
