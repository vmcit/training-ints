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
        return jdbcTemplate.update("INSERT INTO employess (id, full_name , gender ,birth_date ,department_id,) VALUES(?,?,?,?,?)",
                new Object[] { employee.getId(), employee.getFull_name(), employee.getGender(),
                        employee.getBirth_date(),employee.getDepartment_id()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employee SET id=?, full_name=?, gender=?,birth_date=?,department_id=? WHERE id=?",
                new Object[] { employee.getId(), employee.getFull_name(), employee.getGender(),
                        employee.getBirth_date(),employee.getDepartment_id()});
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

    @Override
    public Employee findByDept(String dept) {
        try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM " +
                            "employees left join department on employees.department_id = department.department_id  " +
                            "WHERE department_name=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), dept);

            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Employee findByLocation(String loc) {
        try {
            Employee employee = jdbcTemplate.queryForObject("select * from employees left join department on employees.department_id = department.department_id left join department_location on\n" +
                            "department.location_id = department_location.location_id\n" +
                            "where adress=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), loc);

            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}

