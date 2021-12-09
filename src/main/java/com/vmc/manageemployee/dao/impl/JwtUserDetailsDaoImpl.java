package com.vmc.manageemployee.dao.impl;

import com.vmc.manageemployee.dao.JwtUserDetailsDao;
import com.vmc.manageemployee.models.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JwtUserDetailsDaoImpl implements JwtUserDetailsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserSystem findByUsername(String username) {
        try {
            UserSystem userSystem = jdbcTemplate.queryForObject("SELECT * FROM User_Company  WHERE username=?",
                    BeanPropertyRowMapper.newInstance(UserSystem.class), username);

            return userSystem;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
