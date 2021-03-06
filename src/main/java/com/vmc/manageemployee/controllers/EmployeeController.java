package com.vmc.manageemployee.controllers;

import java.util.ArrayList;
import java.util.List;

import com.vmc.manageemployee.models.Employee;
import com.vmc.manageemployee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller use JDBC
 * */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/jdbc")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = new ArrayList<Employee>();

           employeeService.findAll().forEach(employees::add);

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeelById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/finddept/{dept}")
    public ResponseEntity<Employee> getEmployeelByDept(@PathVariable("dept") String dept) {
        Employee employee = employeeService.findByDept(dept);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/findloc/{loc}")
    public ResponseEntity<Employee> getEmployeelByLoc(@PathVariable("loc") String loc) {
        Employee employee = employeeService.findByLocation(loc);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.save(new Employee( employee.getId(), employee.getFull_name(), employee.getGender(),
                    employee.getBirth_date(),employee.getDepartment_id()));
            return new ResponseEntity<>("Employee was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(id);

        if (employee1 != null) {
            employee1.setId((int) id);
            employee1.setFull_name(employee.getFull_name());
            employee1.setGender(employee.getGender());
            employee1.setBirth_date(employee.getBirth_date());
            employee1.setDepartment_id(employee.getDepartment_id());

            employeeService.update(new Employee(employee1.getId(),
                    employee1.getFull_name(),employee1.getGender(),employee1.getBirth_date(),employee1.getDepartment_id()));
            return new ResponseEntity<>("Employee was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Employee with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        try {
            int result = employeeService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Employee with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Employee was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete employee.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employees")
    public ResponseEntity<String> deleteAllEployees() {
        try {
            int numRows = employeeService.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " employee(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete employees.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
