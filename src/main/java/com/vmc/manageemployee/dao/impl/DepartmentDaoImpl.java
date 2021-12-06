package com.vmc.manageemployee.dao.impl;

import com.vmc.manageemployee.dao.DepartmentDao;
import com.vmc.manageemployee.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Department dept) {
        return jdbcTemplate.update("INSERT INTO departent (DepartmentId, DepartmentName, Duty ,LocationId ) VALUES(?,?,?,?)",
                new Object[] { dept.getDepartmentId(), dept.getDepartmentName(), dept.getDuty(),
                        dept.getLocationId()});
    }

    @Override
    public int update(Department dept) {
        return jdbcTemplate.update("UPDATE departent SET DepartmentId=?, DepartmentName=?, Duty=?,LocationId=? WHERE DepartmentId=?",
                new Object[] { dept.getDepartmentId(), dept.getDepartmentName(), dept.getDuty(),
                        dept.getLocationId()});
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM departent WHERE DepartmentId=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from departent");
    }

    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query("SELECT * from departent",
                BeanPropertyRowMapper.newInstance(Department.class));
    }

    @Override
    public Department findById(Long id) {
        try {
            Department department = jdbcTemplate.queryForObject("SELECT * FROM departent WHERE DepartmentId=?",
                    BeanPropertyRowMapper.newInstance(Department.class), id);

            return department;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
