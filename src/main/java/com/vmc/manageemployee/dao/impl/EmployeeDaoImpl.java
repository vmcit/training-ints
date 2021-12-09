package com.vmc.manageemployee.dao.impl;


import com.vmc.manageemployee.dao.EmployeeDao;
import com.vmc.manageemployee.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employess (Id, FullName , Gender ,BirthDate ,DepartmentId,) VALUES(?,?,?,?,?)",
                new Object[] { employee.getId(), employee.getFullName(), employee.getGender(),
                        employee.getBirthDate(),employee.getDepartmentId()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employee SET Id=?, FullName=?, Gender=?,BirthDate=?,DepartmentId=? WHERE id=?",
                new Object[] { employee.getId(), employee.getFullName(), employee.getGender(),
                        employee.getBirthDate(),employee.getDepartmentId()});
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from employees");
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * from employees", BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public Employee findById(Long id) {
         try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);

            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}

