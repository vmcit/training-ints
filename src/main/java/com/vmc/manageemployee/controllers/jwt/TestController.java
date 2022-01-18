package com.vmc.manageemployee.controllers.jwt;

import com.vmc.manageemployee.dao.DepartmentLocationTestDao;
import com.vmc.manageemployee.entities.DepartmentLocationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class create to test
 * */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/test")
public class TestController {

    @Autowired
    DepartmentLocationTestDao dao;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/testapp")
    public ResponseEntity<List<DepartmentLocationTest>> abc () {
        try {
            List<DepartmentLocationTest> d =  dao.selectAll();
            return  new ResponseEntity<>(d, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
