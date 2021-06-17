package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.User_Role;
import com.fpoly.repositories.User_RoleRepository;

@Service
public class User_RoleService {

	@Autowired
	User_RoleRepository user_RoleRepository;
	
	public Optional<User_Role> findById(int id){
		return user_RoleRepository.findById(id);
	}
}
