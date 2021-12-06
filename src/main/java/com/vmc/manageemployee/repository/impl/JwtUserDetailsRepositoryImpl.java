package com.vmc.manageemployee.repository.impl;

import com.vmc.manageemployee.dao.JwtUserDetailsDao;
import com.vmc.manageemployee.model.UserSystem;
import com.vmc.manageemployee.repository.JwtUserDetailsRepository;
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
