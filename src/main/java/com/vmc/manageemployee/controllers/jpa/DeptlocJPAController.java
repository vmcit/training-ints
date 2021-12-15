package com.vmc.manageemployee.controllers.jpa;


import com.vmc.manageemployee.dto.DepartmentLocationDTO;
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

            departmentLocationList =  departmentLocationRepository.findAll();
            if (departmentLocationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(departmentLocationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departmentlocations/{id}")
    public ResponseEntity<DepartmentLocationDTO> getDepartmentLocationById(@PathVariable("id") long id) {
        Optional<DepartmentLocation> departmentLocation = departmentLocationRepository.findById((int) id);

        if (departmentLocation.isPresent()) {
            DepartmentLocationDTO departmentLocationDTO = new DepartmentLocationDTO(departmentLocation.get().getLocationId(),
                    departmentLocation.get().getAdress(),departmentLocation.get().getCity(),departmentLocation.get().getCountry());
            return new ResponseEntity<>(departmentLocationDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/departmentlocations")
    public ResponseEntity<String> createDepartmentLocation(@Validated @RequestBody DepartmentLocationDTO departmentLocationDTO) {
        try {
           departmentLocationRepository
                    .save(new DepartmentLocation(departmentLocationDTO.getLocation_id(), departmentLocationDTO.getAdress(),departmentLocationDTO.getCity(),
                            departmentLocationDTO.getCountry()));
            return new ResponseEntity<>("Department Locations was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/departmentlocations/{id}")
    public ResponseEntity<String> updateDepartmentLocation(@PathVariable("id") long id,@Validated @RequestBody DepartmentLocationDTO departmentLocationDTO) {
        Optional<DepartmentLocation> departmentLocation1 = departmentLocationRepository.findById((int) id);

        if (departmentLocation1.isPresent()) {
            DepartmentLocation departmentLocation2 = departmentLocation1.get();
            departmentLocation2.setLocationId(departmentLocationDTO.getLocation_id());
            departmentLocation2.setAdress(departmentLocationDTO.getAdress());
            departmentLocation2.setCity(departmentLocationDTO.getCity());
            departmentLocation2.setCountry(departmentLocationDTO.getCountry());
            departmentLocationRepository.save(departmentLocation2);
            return new ResponseEntity<>("Department Locations was update successfully.", HttpStatus.OK);
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
