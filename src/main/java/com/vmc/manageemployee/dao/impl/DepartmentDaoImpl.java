package com.vmc.manageemployee.dao.impl;

import com.vmc.manageemployee.dao.DepartmentDao;
import com.vmc.manageemployee.models.Department;
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
        return jdbcTemplate.update("INSERT INTO DEPARTMENT (department_id, department_name, Duty ,location_id ) VALUES(?,?,?,?)",
                new Object[] { dept.getDepartment_id(), dept.getDepartment_name(), dept.getDuty(),
                        dept.getLocation_id()});
    }

    @Override
    public int update(Department dept) {
        return jdbcTemplate.update("UPDATE DEPARTMENT SET department_id=?, department_name=?, Duty=?,location_id=? WHERE department_id=?",
                new Object[] { dept.getDepartment_id(), dept.getDepartment_name(), dept.getDuty(),
                        dept.getLocation_id()},dept.getDepartment_id());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM DEPARTMENT WHERE department_id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from DEPARTMENT");
    }

    @Override
    public List<Department> findAll() {
        return jdbcTemplate.query("SELECT * from DEPARTMENT",
                BeanPropertyRowMapper.newInstance(Department.class));
    }

    @Override
    public Department findById(Long id) {
        try {
            Department department = jdbcTemplate.queryForObject("SELECT * FROM DEPARTMENT WHERE department_id=?",
                    BeanPropertyRowMapper.newInstance(Department.class), id);

            return department;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
