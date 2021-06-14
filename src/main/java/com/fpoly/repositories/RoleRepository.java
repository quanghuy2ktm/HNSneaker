package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
