package com.vmc.manageemployee.dao;

import com.vmc.manageemployee.model.DepartmentLocation;

import java.util.List;

public interface DepartmentLocationDao {

    int save(DepartmentLocation deptloc);
    int update(DepartmentLocation deptloc);
    int deleteById(Long id);
    int deleteAll();
    List<DepartmentLocation> findAll();
    DepartmentLocation findById(Long id);
}
