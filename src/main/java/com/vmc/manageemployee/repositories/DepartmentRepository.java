package com.vmc.manageemployee.repositories;

import com.vmc.manageemployee.models.Department;

import java.util.List;

public interface DepartmentRepository {

    int save(Department dept);
    int update(Department dept);
    int deleteById(Long id);
    int deleteAll();
    List<Department> findAll();
    Department findById(Long id);
}
