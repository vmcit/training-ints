package com.vmc.manageemployee.repository.impl;

import com.vmc.manageemployee.dao.DepartmentLocationDao;
import com.vmc.manageemployee.model.DepartmentLocation;
import com.vmc.manageemployee.repository.DepartmentLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DepartmentLocationRepositoryImpl implements DepartmentLocationRepository {

    @Autowired
    DepartmentLocationDao departmentLocationDao;
    @Override
    public int save(DepartmentLocation deptloc) {
        return departmentLocationDao.save(deptloc);
    }

    @Override
    public int update(DepartmentLocation deptloc) {
        return departmentLocationDao.update(deptloc);
    }

    @Override
    public int deleteById(Long id) {
        return departmentLocationDao.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return departmentLocationDao.deleteAll();
    }

    @Override
    public List<DepartmentLocation> findAll() {
        return departmentLocationDao.findAll();
    }

    @Override
    public DepartmentLocation findById(Long id) {
        return departmentLocationDao.findById(id);
    }
}
