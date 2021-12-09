package com.vmc.manageemployee.services.impl;

import com.vmc.manageemployee.models.Employee;
import com.vmc.manageemployee.repositories.EmployeeRepository;
import com.vmc.manageemployee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public int save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public int deleteById(Long id) {
        return employeeRepository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return employeeRepository.deleteAll();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }
}
