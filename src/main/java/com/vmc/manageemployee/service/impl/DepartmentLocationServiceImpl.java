package com.vmc.manageemployee.service.impl;

import com.vmc.manageemployee.model.DepartmentLocation;
import com.vmc.manageemployee.model.Employee;
import com.vmc.manageemployee.repository.DepartmentLocationRepository;
import com.vmc.manageemployee.repository.EmployeeRepository;
import com.vmc.manageemployee.service.DepartmentLocationService;
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
