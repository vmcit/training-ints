package com.vmc.manageemployee.repository;

import com.vmc.manageemployee.model.Department;

import java.util.List;

public interface DepartmentRepository {

    int save(Department dept);
    int update(Department dept);
    int deleteById(Long id);
    int deleteAll();
    List<Department> findAll();
    Department findById(Long id);
}
