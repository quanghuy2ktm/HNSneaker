package com.fpoly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Role;
import com.fpoly.model.User;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.repositories.User_RoleRepository;
import com.fpoly.service.User_RoleService;

@Controller
@RequestMapping("/userrole/")
public class User_RoleController {

	@Autowired
	User_RoleRepository user_RoleRepository;

	@Autowired
	User_RoleService user_RoleService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@RequestMapping("/get")
	public String getAll(Model model) {

		List<User> user = userRepository.findAll();

		List<Role> role = roleRepository.findAll();

		List<User_Role> uRole = user_RoleRepository.findAll();
		
		model.addAttribute("user", user);

		model.addAttribute("role", role);
		
		model.addAttribute("uroleList", uRole);

		System.out.println("hihi" + user);
		
		System.out.println("hihi" + role);

		System.out.println("hihi" + uRole);

		
		return "urole/show";

	}

	@RequestMapping("/save")
	public String saveURole(Model model, @ModelAttribute("User_Role") User_Role uRole) {

		List<User> user = userRepository.findAll();

		List<Role> role = roleRepository.findAll();

		model.addAttribute("user", user);

		model.addAttribute("role", role);

		user_RoleRepository.save(uRole);
		
		return "redirect:/userrole/get";

	}

	@RequestMapping("/updateForm/{id}")
	public String updateURole(@PathVariable int id, Model model) {
		
		Optional<User_Role> uRole = user_RoleRepository.findById(id);

		if (uRole.isPresent()) {
			model.addAttribute("uRole", uRole.get());
		}

		List<User> user = userRepository.findAll();

		List<Role> role = roleRepository.findAll();

		model.addAttribute("user", user);

		model.addAttribute("role", role);

		return "urole/update";
	}

	@PostMapping("/update")
	public String doUpdateURole(Model model, @ModelAttribute("User_Role") User_Role user_Role) {

		user_RoleRepository.save(user_Role);

		// List<Address> addressList = addressRepos.findAll();

		return "redirect:/userrole/get";

	}

	@RequestMapping("/delete/{id}")
	public String deleteURole(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("User_Role") User_Role uRole) {

		User_Role userrole = user_RoleRepository.getById(id);

		user_RoleRepository.delete(userrole);

		return "redirect:/userrole/get";
	}

}
