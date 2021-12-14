package com.vmc.manageemployee.repositories.jpa;

import com.vmc.manageemployee.entities.DepartmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Integer> {

}
