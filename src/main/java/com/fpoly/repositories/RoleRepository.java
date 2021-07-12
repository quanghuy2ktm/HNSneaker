package com.fpoly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value="select * from Role where RoleName=?1", nativeQuery = true)
	Role findByRoleName(String rolename);

}
