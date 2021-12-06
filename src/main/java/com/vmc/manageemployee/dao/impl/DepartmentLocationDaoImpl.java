package com.vmc.manageemployee.dao.impl;

import com.vmc.manageemployee.dao.DepartmentLocationDao;
import com.vmc.manageemployee.model.Department;
import com.vmc.manageemployee.model.DepartmentLocation;
import com.vmc.manageemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentLocationDaoImpl implements DepartmentLocationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(DepartmentLocation deptloc) {
        return jdbcTemplate.update("INSERT INTO department_location (LocationId, Adress ,City ,Country) VALUES(?,?,?,?)",
                new Object[] { deptloc.getLocationId(), deptloc.getAdress(), deptloc.getCity(),
                        deptloc.getCountry()});
    }

    @Override
    public int update(DepartmentLocation deptloc) {
        return jdbcTemplate.update("UPDATE department_location SET LocationId=?, Adress=?, City=?,Country=? WHERE LocationId=?",
                new Object[] { deptloc.getLocationId(), deptloc.getAdress(), deptloc.getCity(),
                        deptloc.getCountry()});
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM department_location WHERE LocationId=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from department_location");
    }

    @Override
    public List<DepartmentLocation> findAll() {
        return jdbcTemplate.query("SELECT * from department_location", BeanPropertyRowMapper.newInstance(DepartmentLocation.class));
    }

    @Override
    public DepartmentLocation findById(Long id) {
        try {
            DepartmentLocation departmentLocation = jdbcTemplate.queryForObject("SELECT * FROM department_location WHERE LocationId=?",
                    BeanPropertyRowMapper.newInstance(DepartmentLocation.class), id);

            return departmentLocation;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
