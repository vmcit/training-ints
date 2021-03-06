package com.vmc.manageemployee.services.impl;

import com.vmc.manageemployee.models.Department;
import com.vmc.manageemployee.repositories.DepartmentRepository;
import com.vmc.manageemployee.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class departmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public int save(Department dept) {
        return departmentRepository.save(dept);
    }

    @Override
    public int update(Department dept) {
        return departmentRepository.update(dept);
    }

    @Override
    public int deleteById(Long id) {
        return departmentRepository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return departmentRepository.deleteAll();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id);
    }
}
