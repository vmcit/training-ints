package com.vmc.manageemployee.services.impl;

import com.vmc.manageemployee.models.DepartmentLocation;
import com.vmc.manageemployee.repositories.DepartmentLocationRepository;
import com.vmc.manageemployee.services.DepartmentLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentLocationServiceImpl implements DepartmentLocationService {

    @Autowired
    DepartmentLocationRepository departmentLocationRepository;


    @Override
    public int save(DepartmentLocation deptloc) {
        return departmentLocationRepository.save(deptloc);
    }

    @Override
    public int update(DepartmentLocation deptloc) {
        return departmentLocationRepository.update(deptloc);
    }

    @Override
    public int deleteById(Long id) {
        return departmentLocationRepository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return departmentLocationRepository.deleteAll();
    }

    @Override
    public List<DepartmentLocation> findAll() {
        return departmentLocationRepository.findAll();
    }

    @Override
    public DepartmentLocation findById(Long id) {
        return departmentLocationRepository.findById(id);
    }
}
