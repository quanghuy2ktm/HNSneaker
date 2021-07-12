package com.fpoly.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.config.UserSecurityService;
import com.fpoly.model.Role;
import com.fpoly.model.User;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.repositories.User_RoleRepository;
import com.fpoly.service.UserService;



@Controller
public class AccountController {
	
	@Autowired
	RoleRepository roleRepos;
	
	@Autowired
	private User_RoleRepository userRoleRepo;
	
	@Autowired
	UserRepository repos;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String log(Model model) {
		model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
		model.addAttribute("emailExists", model.asMap().get("emailExists"));
		return "myAccount";
	}
	
	@RequestMapping(value="/new-user", method=RequestMethod.POST)
	public String newUserPost(@ModelAttribute("user") User user, BindingResult bindingResults, 
							  RedirectAttributes redirectAttributes, Model model) {
		
		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());
		
		boolean invalidFields = false;
		
		if (bindingResults.hasErrors()) {
			return "redirect:/login";
		}		
		if (userService.findByUsername(user.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("usernameExists", true);
			invalidFields = true;
		}
		if (userService.findByEmail(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("emailExists", true);
			invalidFields = true;
		}	
		if (invalidFields) {
			return "redirect:/login";
		}	
		System.out.println("-=-----------");
		System.out.println("user Detail: "+user.getUsername()+" - " +user.getPassword()+" - " +user.getEmail());
//		user = userService.createUser(user.getUsername(), user.getPassWord(),  user.getEmail(), Arrays.asList("ROLE_USER"));	
		
		User userTemp = new User();
		userTemp.setEmail(user.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userTemp.setPassWord(user.getPassword());
		userTemp.setUsername(user.getUsername());
		repos.save(userTemp);
		
		//thêm quyền;
		if(userTemp !=null) {
		    Role role = roleRepos.findByRoleName("ROLE_USER");
		    User_Role u = new User_Role();
		    u.setRole(role);
			u.setUser(userTemp);
			userRoleRepo.save(u);
		}
		userSecurityService.authenticateUser(user.getUsername());
		
		return "myAccount";
//		return "redirect:/my-profile";  
	}
	
}
