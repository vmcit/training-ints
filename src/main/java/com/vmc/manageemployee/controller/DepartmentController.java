package com.vmc.manageemployee.controller;

import com.vmc.manageemployee.model.Department;
import com.vmc.manageemployee.model.Employee;
import com.vmc.manageemployee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartment(@RequestParam(required = false) String id) {
        try {
            List<Department> departments = new ArrayList<Department>();

            departmentService.findAll().forEach(departments::add);

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
        Department department = departmentService.findById(id);

        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@RequestBody Department dept) {
        try {
            departmentService.save(new Department( dept.getDepartmentId(), dept.getDepartmentName(), dept.getDuty(),
                    dept.getLocationId()));
            return new ResponseEntity<>("Department was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable("id") long id, @RequestBody Department dept) {
        Department department = departmentService.findById(id);

        if (department != null) {
            department.setDepartmentId( (int) id);
            department.setDepartmentName(dept.getDepartmentName());
            department.setDuty(dept.getDuty());
            department.setLocationId(dept.getLocationId());


            departmentService.update(department);
            return new ResponseEntity<>("Department was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Department with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long id) {
        try {
            int result = departmentService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Department with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Department was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete department.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departments")
    public ResponseEntity<String> deleteAllDepartments() {
        try {
            int numRows = departmentService.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " employee(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete departments.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
