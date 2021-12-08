package com.vmc.manageemployee.repository;


import com.vmc.manageemployee.common.jwt.ERole;
import com.vmc.manageemployee.model.jwt.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}
