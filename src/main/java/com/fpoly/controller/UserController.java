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

import com.fpoly.model.Address;
import com.fpoly.model.User;
import com.fpoly.repositories.AddressRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	UserService userService;

	@RequestMapping("/getUser")
	public String getAllUser(Model model) {

		List<User> userList = userRepository.findAll();

		model.addAttribute("userList", userList);

		return "user/show";

	}

	@RequestMapping("/save")
	public String saveUser(Model model, @ModelAttribute("User") User user) {

		userRepository.save(user);

//		List<User> userList = userRepository.findAll();
//
//		model.addAttribute("userList", userList);

		return "redirect:/user/getUser";
	}

	@RequestMapping("/update/{id}")
	public String updateUser(@PathVariable int id, Model model) {

		Optional<User> user = userService.findById(id);


		if (user.isPresent()) {
			model.addAttribute("userTemp", user.get());
		}
		
		List<Address> address1 = addressRepository.findAll();

		model.addAttribute("address1", address1);
		
		System.out.println("ddaayd dadsydigahkbsjdn " +address1);
		
		return "user/Update";
		
		
		
	}

	@PostMapping("/update")
	public String doUpdateUser(Model model, @ModelAttribute("User") User user) {

		List<Address> address1 = addressRepository.findAll();

		model.addAttribute("address1", address1);

		userRepository.save(user);

		model.addAttribute("userList", userRepository.findAll());

		return "redirect:/user/getUser";

	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Integer id, @ModelAttribute("User") User user) {

		User userTemp = userRepository.getById(id);

		userRepository.delete(userTemp);

		return "redirect:/user/getUser";

	}

}
