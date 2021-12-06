package com.vmc.manageemployee.service;



import com.vmc.manageemployee.model.Department;

import java.util.List;

public interface DepartmentService {

    int save(Department dept);
    int update(Department dept);
    int deleteById(Long id);
    int deleteAll();
    List<Department> findAll();
    Department findById(Long id);
}
