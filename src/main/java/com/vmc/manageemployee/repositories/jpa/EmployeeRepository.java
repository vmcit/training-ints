package com.vmc.manageemployee.repositories.jpa;


import com.vmc.manageemployee.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}
