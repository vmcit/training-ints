package com.vmc.manageemployee.controllers.jpa;

import com.vmc.manageemployee.dto.DepartmentDTO;
import com.vmc.manageemployee.entities.Department;
import com.vmc.manageemployee.entities.DepartmentLocation;
import com.vmc.manageemployee.entities.Employees;
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
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
            departments = departmentRepository.findAll();
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") long id) {
        Optional<Department> department = departmentRepository.findById((int) id);

        if (department.isPresent()) {
            DepartmentDTO departmentDTO = new DepartmentDTO(department.get().getDepartmentId(), department.get().getDepartmentName(),
                    department.get().getDuty(), department.get().getDepartmentLocation().getLocationId());
            return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departmentlocations/{location_id}/departments")
    public ResponseEntity<String> createDepartment(@PathVariable(value = "location_id") long id, @Validated @RequestBody DepartmentDTO departmentDTO) {

        try {
            departmentLocationRepository.findById(Math.toIntExact(id)).map(departmentlocation -> {
                Department department = new Department();
                department.setDepartmentLocation(departmentlocation);
                department.setDepartmentId(departmentDTO.getDepartment_id());
                department.setDepartmentName(departmentDTO.getDepartment_name());
                department.setDuty(departmentDTO.getDuty());
                departmentRepository.save(department);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("location_id " + id + " not found"));
            return new ResponseEntity<>("Department was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/departmentlocations/{location_id}/departments/{department_id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("location_id") long locationid,
                                                   @PathVariable("department_id") long departmentid,
                                                   @RequestBody DepartmentDTO departmentDTO) {
        try {
            departmentLocationRepository.findById((int) locationid).map(departmentLocation -> {
                Department department = new Department();
                department.setDepartmentLocation(departmentLocation);
                departmentRepository.findById((int) departmentid).map(department1 -> {
                    department.setDepartmentId(departmentDTO.getDepartment_id());
                    department.setDepartmentName(departmentDTO.getDepartment_name());
                    department.setDuty(departmentDTO.getDuty());
                    departmentRepository.save(department);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("location_id " + locationid + "not found"));
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + departmentid + " not found"));

            return new ResponseEntity<>("Department was updated successfully.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departmentlocations/{location_id}/departments/{department_id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable("location_id") long locationid, @PathVariable("department_id") long departmentid) {
        try {
            departmentLocationRepository.findById((int) locationid).map(departmentLocation -> {
                Department department = new Department();
                department.setDepartmentLocation(departmentLocation);
                departmentRepository.findById((int) departmentid).map(department1 -> {
                    department.setDepartmentId(department1.getDepartmentId());
                    department.setDepartmentName(department1.getDepartmentName());
                    department.setDuty(department1.getDuty());
                    departmentRepository.delete(department);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("location_id " + locationid + "not found"));
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + departmentid + " not found"));
            return new ResponseEntity<>("Department was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}





