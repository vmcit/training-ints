package com.vmc.manageemployee.repositories;

import com.vmc.manageemployee.models.Employee;

import java.util.List;

public interface EmployeeRepository {

    int save(Employee employee);
    int update(Employee employee);
    int deleteById(Long id);
    int deleteAll();
    List<Employee> findAll();
    Employee findById(Long id);
    Employee findByDept(String dept);
    Employee findByLocation(String loc);
}
