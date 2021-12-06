package com.vmc.manageemployee.dao;

import com.vmc.manageemployee.model.Department;

import java.util.List;


public interface DepartmentDao {

    int save(Department dept);
    int update(Department dept);
    int deleteById(Long id);
    int deleteAll();
    List<Department> findAll();
    Department findById(Long id);
}
