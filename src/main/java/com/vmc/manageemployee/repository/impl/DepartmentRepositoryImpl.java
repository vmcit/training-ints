package com.vmc.manageemployee.repository.impl;

import com.vmc.manageemployee.dao.DepartmentDao;
import com.vmc.manageemployee.model.Department;
import com.vmc.manageemployee.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public int save(Department dept) {
        return departmentDao.save(dept);
    }

    @Override
    public int update(Department dept) {
        return departmentDao.update(dept);
    }

    @Override
    public int deleteById(Long id) {
        return departmentDao.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return departmentDao.deleteAll();
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id);
    }
}
