package com.vmc.manageemployee.controllers.jpa;

import com.vmc.manageemployee.entities.Department;
import com.vmc.manageemployee.entities.DepartmentLocation;
import com.vmc.manageemployee.exception.ResourceNotFoundException;
import com.vmc.manageemployee.repositories.jpa.DepartmentLocationRepository;
import com.vmc.manageemployee.repositories.jpa.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Controller use JPA
 * */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/jpa")
public class DeptJPAController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentLocationRepository departmentLocationRepository;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartment() {
        try {
            List<Department> departments = new ArrayList<>();


            departmentRepository.findAll().forEach(departments::add);

            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
        Optional<Department> department = departmentRepository.findById((int) id);

        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departmentlocations/{location_id}/departments")
    public ResponseEntity<DepartmentLocation> createDepartment(@PathVariable(value = "location_id") long id, @Validated @RequestBody Department department) {

        try {
            departmentLocationRepository.findById(Math.toIntExact(id)).map(departmentlocation -> {
                department.setDepartmentLocation(departmentlocation);
                departmentRepository.save(department);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("location_id " + id + " not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/departmentlocations/{location_id}/departments/{department_id}")
    public ResponseEntity<DepartmentLocation> updateDepartment(@PathVariable("location_id") long locationid,
                                                               @PathVariable("department_id") long departmentid,
                                                               @RequestBody Department department) {
        try {
            if (!departmentLocationRepository.existsById(Math.toIntExact(locationid))) {
                throw new ResourceNotFoundException("location_id " + locationid + " not found");
            }

            departmentRepository.findById(Math.toIntExact(departmentid)).map(department1 -> {
                department1.setDepartmentId(department.getDepartmentId());
                department1.setDepartmentName(department.getDepartmentName());
                department1.setDuty(department.getDuty());
                departmentRepository.save(department1);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("departmentid " + departmentid + "not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @DeleteMapping ("/departmentlocations/{location_id}/departments/{department_id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable("location_id") long locationid, @PathVariable("department_id") long departmentid) {
        try {
            if (!departmentLocationRepository.existsById(Math.toIntExact(locationid))) {
                throw new ResourceNotFoundException("location_id " + locationid + " not found");
            }

            departmentRepository.findById(Math.toIntExact(departmentid)).map(department1 -> {
                departmentRepository.delete(department1);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("departmentid " + departmentid + "not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}





