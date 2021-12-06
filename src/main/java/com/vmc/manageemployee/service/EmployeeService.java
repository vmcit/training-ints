package com.vmc.manageemployee.service;

import com.vmc.manageemployee.model.Employee;

import java.util.List;

public interface EmployeeService {

    int save(Employee employee);
    int update(Employee employee);
    int deleteById(Long id);
    int deleteAll();
    List<Employee> findAll();
    Employee findById(Long id);

}
