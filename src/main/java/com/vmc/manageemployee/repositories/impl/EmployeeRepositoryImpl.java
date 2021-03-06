package com.vmc.manageemployee.repositories.impl;

import com.vmc.manageemployee.dao.EmployeeDao;
import com.vmc.manageemployee.models.Employee;
import com.vmc.manageemployee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public int save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public int deleteById(Long id) {
        return employeeDao.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return employeeDao.deleteAll();
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public Employee findByDept(String dept) {
        return employeeDao.findByDept(dept);
    }

    @Override
    public Employee findByLocation(String loc) {
        return employeeDao.findByLocation(loc);
    }

}
