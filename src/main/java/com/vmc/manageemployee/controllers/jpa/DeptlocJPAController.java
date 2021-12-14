package com.vmc.manageemployee.controllers.jpa;


import com.vmc.manageemployee.entities.DepartmentLocation;
import com.vmc.manageemployee.exception.ResourceNotFoundException;
import com.vmc.manageemployee.repositories.jpa.DepartmentLocationRepository;
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
public class DeptlocJPAController {

    @Autowired
    DepartmentLocationRepository departmentLocationRepository;

    @GetMapping("/departmentlocations")
    public ResponseEntity<List<DepartmentLocation>> getAllDepartmentLocation() {
        try {
            List<DepartmentLocation> departmentLocationList = new ArrayList<>();


                departmentLocationRepository.findAll().forEach(departmentLocationList::add);

            if (departmentLocationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departmentLocationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departmentlocations/{id}")
    public ResponseEntity<DepartmentLocation> getDepartmentLocationById(@PathVariable("id") long id) {
        Optional<DepartmentLocation> departmentLocation = departmentLocationRepository.findById((int) id);

        if (departmentLocation.isPresent()) {
            return new ResponseEntity<>(departmentLocation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departmentlocations")
    public ResponseEntity<DepartmentLocation> createDepartmentLocation(@Validated @RequestBody DepartmentLocation departmentLocation) {
        try {
            DepartmentLocation departmentLocation1 = departmentLocationRepository
                    .save(new DepartmentLocation(departmentLocation.getLocationId(), departmentLocation.getAdress(),departmentLocation.getCity(),
                            departmentLocation.getCountry()));
            return new ResponseEntity<>(departmentLocation1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departmentlocations/{id}")
    public ResponseEntity<DepartmentLocation> updateDepartmentLocation(@PathVariable("id") long id,@Validated @RequestBody DepartmentLocation departmentLocation) {
        Optional<DepartmentLocation> departmentLocation1 = departmentLocationRepository.findById((int) id);

        if (departmentLocation1.isPresent()) {
            DepartmentLocation departmentLocation2 = departmentLocation1.get();
            departmentLocation2.setLocationId(departmentLocation.getLocationId());
            departmentLocation2.setAdress(departmentLocation.getAdress());
            departmentLocation2.setCity(departmentLocation.getCity());
            return new ResponseEntity<>(departmentLocationRepository.save(departmentLocation2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/departmentlocations/{id}")
    public ResponseEntity<Object> deleteDepartmentLocation(@PathVariable("id") long id) {
        try {
            departmentLocationRepository.findById((int) id).map(departmentLocation -> {
                departmentLocationRepository.delete(departmentLocation);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("LocationId " + id + " not found"));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/departmentlocations")
    public ResponseEntity<HttpStatus> deleteDepartmentLocation() {
        try {
            departmentLocationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
