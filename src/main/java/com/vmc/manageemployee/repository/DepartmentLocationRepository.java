package com.vmc.manageemployee.repository;

import com.vmc.manageemployee.model.DepartmentLocation;

import java.util.List;

public interface DepartmentLocationRepository {

    int save(DepartmentLocation deptloc);
    int update(DepartmentLocation deptloc);
    int deleteById(Long id);
    int deleteAll();
    List<DepartmentLocation> findAll();
    DepartmentLocation findById(Long id);
}
