package com.vmc.manageemployee.controllers;


import com.vmc.manageemployee.models.DepartmentLocation;
import com.vmc.manageemployee.services.DepartmentLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/jdbc")
public class DepartmentLocationController {

    @Autowired
    DepartmentLocationService departmentLocationService;

    @GetMapping("/departmentlocations")
    public ResponseEntity<List<DepartmentLocation>> getAllDepartmentLocation() {
        try {
            List<DepartmentLocation> departmentLocations = new ArrayList<DepartmentLocation>();

            departmentLocationService.findAll().forEach(departmentLocations::add);

            if (departmentLocations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departmentLocations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departmentlocations/{id}")
    public ResponseEntity<DepartmentLocation> getDepartmentLocationById(@PathVariable("id") long id) {
        DepartmentLocation departmentLocation = departmentLocationService.findById(id);

        if (departmentLocation != null) {
            return new ResponseEntity<>(departmentLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departmentlocations")
    public ResponseEntity<String> createDepartmentLocation(@RequestBody DepartmentLocation deptloc) {
        try {
            departmentLocationService.save(new DepartmentLocation( deptloc.getLocation_id(), deptloc.getAdress(), deptloc.getCity(),
                    deptloc.getCountry()));
            return new ResponseEntity<>("DepartmentLocation was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departmentlocations/{id}")
    public ResponseEntity<String> updateDepartmentLocation(@PathVariable("id") long id, @RequestBody DepartmentLocation deptloc) {
        DepartmentLocation departmentlocation = departmentLocationService.findById(id);

        if (departmentlocation != null) {
            departmentlocation.setLocation_id( (int) id);
            departmentlocation.setAdress(deptloc.getAdress());
            departmentlocation.setCity(deptloc.getCity());
            departmentlocation.setCountry(deptloc.getCountry());


            departmentLocationService.update(departmentlocation);
            return new ResponseEntity<>("Departmentlocation was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Departmentlocation with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departmentlocations/{id}")
    public ResponseEntity<String> deleteDepartmentLocation(@PathVariable("id") long id) {
        try {
            int result = departmentLocationService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Departmentlocation with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Departmentlocation was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete Departmentlocation.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departmentlocations")
    public ResponseEntity<String> deleteAllDepartmentLocations() {
        try {
            int numRows = departmentLocationService.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Departmentlocation(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete departmentlocations.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
