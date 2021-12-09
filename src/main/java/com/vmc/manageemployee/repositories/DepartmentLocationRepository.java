package com.vmc.manageemployee.repositories;

import com.vmc.manageemployee.models.DepartmentLocation;

import java.util.List;

public interface DepartmentLocationRepository {

    int save(DepartmentLocation deptloc);
    int update(DepartmentLocation deptloc);
    int deleteById(Long id);
    int deleteAll();
    List<DepartmentLocation> findAll();
    DepartmentLocation findById(Long id);
}
