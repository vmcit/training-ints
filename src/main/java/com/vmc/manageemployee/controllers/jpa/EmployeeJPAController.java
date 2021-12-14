package com.vmc.manageemployee.controllers.jpa;


import com.vmc.manageemployee.entities.Department;
import com.vmc.manageemployee.entities.DepartmentLocation;
import com.vmc.manageemployee.entities.Employees;
import com.vmc.manageemployee.exception.ResourceNotFoundException;
import com.vmc.manageemployee.repositories.jpa.DepartmentRepository;
import com.vmc.manageemployee.repositories.jpa.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/jpa")
public class EmployeeJPAController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getAllEmployees() {
        try {
            List<Employees> employees = new ArrayList<>();


            employeeRepository.findAll().forEach(employees::add);

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Employees> getEmployeelById(@PathVariable("id") long id) {
        Optional<Employees> employees = employeeRepository.findById((int) id);

        if (employees.isPresent()) {
            return new ResponseEntity<>(employees.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departments/{department_id}/employees")
    public ResponseEntity<DepartmentLocation> createEmployee(@PathVariable(value = "department_id") long id, @Validated @RequestBody Employees employees) {

        try {
            departmentRepository.findById((int) id).map(department -> {
                employees.setDepartment(department);
                employeeRepository.save(employees);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + id + " not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("/departments/{department_id}/employees/{id}")
    public ResponseEntity<DepartmentLocation> updateEmployee(@PathVariable("department_id") long departmentid,
                                                               @PathVariable("id") long employeeid,
                                                               @RequestBody Employees employees) {
        try {
            if (!departmentRepository.existsById((int) departmentid)) {
                throw new ResourceNotFoundException("department_id " + departmentid + " not found");
            }

            employeeRepository.findById((int) employeeid).map(employees1 -> {
                employees1.setId(employees.getId());
                employees1.setFullName(employees.getFullName());
                employees1.setGender(employees.getGender());
                employees1.setGender(employees.getBirthDate());
                employeeRepository.save(employees1);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("employee_id " + employeeid + "not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/departments/{department_id}/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("department_id") long departmentid, @PathVariable("id") long employeeid) {
        try {
            if (!departmentRepository.existsById((int) departmentid)) {
                throw new ResourceNotFoundException("department_id " + departmentid + " not found");
            }

            employeeRepository.findById(Math.toIntExact(departmentid)).map(employees -> {
                employeeRepository.delete(employees);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("employee_id " + employeeid + "not found"));

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
