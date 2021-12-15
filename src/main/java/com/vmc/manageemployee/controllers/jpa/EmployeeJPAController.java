package com.vmc.manageemployee.controllers.jpa;


import com.vmc.manageemployee.dto.EmployeeDTO;
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

/**
 * Controller use JPA
 */
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


            employees = employeeRepository.findAll();

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeelById(@PathVariable("id") long id) {
        Optional<Employees> employees = employeeRepository.findById((int) id);
        if (employees.isPresent()) {
            EmployeeDTO EmployeeDTO = new EmployeeDTO(employees.get().getId(),
                    employees.get().getFullName(), employees.get().getGender(), employees.get().getBirthDate(), employees.get().getDepartment().getDepartmentId());
            return new ResponseEntity<>(EmployeeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departments/{department_id}/employees")
    public ResponseEntity<String> createEmployee(@PathVariable(value = "department_id") long id, @Validated @RequestBody EmployeeDTO employeeDTO) {
        try {
            departmentRepository.findById((int) id).map(department -> {
                Employees employees1 = new Employees();
                employees1.setDepartment(department);
                employees1.setId(employeeDTO.getId());
                employees1.setFullName(employeeDTO.getFull_name());
                employees1.setGender(employeeDTO.getGender());
                employees1.setBirthDate(employeeDTO.getBirth_date());
                employeeRepository.save(employees1);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + id + " not found"));

            return new ResponseEntity<>("Employee was created successfully.", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/departments/{department_id}/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("department_id") long departmentid,
                                                 @PathVariable("id") long employeeid,
                                                 @RequestBody EmployeeDTO employeeDTO) {
        try {
            departmentRepository.findById((int) departmentid).map(department -> {
                Employees employees1 = new Employees();
                employees1.setDepartment(department);
                employeeRepository.findById((int) employeeid).map(employees2 -> {
                    employees1.setId(employees2.getId());
                    employees1.setFullName(employeeDTO.getFull_name());
                    employees1.setGender(employeeDTO.getGender());
                    employees1.setBirthDate(employeeDTO.getBirth_date());
                    employeeRepository.save(employees1);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("employee_id " + employeeid + "not found"));
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + departmentid + " not found"));

            return new ResponseEntity<>("Employee was updated successfully.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/departments/{department_id}/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("department_id") long departmentid, @PathVariable("id") long employeeid) {
        try {
            departmentRepository.findById((int) departmentid).map(department -> {
                Employees employees1 = new Employees();
                employees1.setDepartment(department);
                employeeRepository.findById((int) employeeid).map(employees2 -> {
                    employees1.setId(employees2.getId());
                    employees1.setFullName(employees2.getFullName());
                    employees1.setGender(employees2.getGender());
                    employees1.setBirthDate(employees2.getBirthDate());
                    employeeRepository.delete(employees1);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("employee_id " + employeeid + "not found"));
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("department_id " + departmentid + " not found"));

            return new ResponseEntity<>("Employee was updated successfully.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
