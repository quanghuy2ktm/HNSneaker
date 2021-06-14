package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.model.User_Role;

@Repository
public interface User_RoleRepository extends JpaRepository<User_Role, Integer> {

}
