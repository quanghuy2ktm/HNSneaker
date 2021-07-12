package com.fpoly.service;


import java.util.List;

import com.fpoly.model.User;

public interface UserService {
	
	com.fpoly.model.User findById(Integer id);
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String email,  String password, List<String> roles);

}
