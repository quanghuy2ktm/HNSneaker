package com.fpoly.config;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.model.Role;
import com.fpoly.model.User;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findById(Integer id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.get();
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User createUser(String username, String password, String email, List<String> roles) {
		User user = findByUsername(username);
		if (user != null) {
			return user;
		} else {
			user = new User();
			
			user.setUsername(username);
//			user.setPassWord(SecurityUtility.passwordEncoder().encode(password));
			user.setEmail(email);		
			
			Set<User_Role> userRoles = new HashSet<>();
			for (String rolename : roles) {
				Role role = roleRepository.findByRoleName(rolename);
				if (role == null) {
					role = new Role();
					role.setRoleName(rolename);
					roleRepository.save(role);
				}
				userRoles.add(new User_Role(user, role));
			}			
			user.setUserRoles(userRoles);
			return userRepository.save(user);
		}
	}
	
}
