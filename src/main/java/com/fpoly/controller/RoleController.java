package com.fpoly.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.model.Address;
import com.fpoly.model.Role;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.service.RoleService;

@Controller
@RequestMapping("/role/")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public String getAllRole(Model model) {
		List<Role> roleList = roleRepository.findAll();
		model.addAttribute("roleList", roleList);
		return "role/show";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRole(Model model, @ModelAttribute("Role") Role role) {
		roleRepository.save(role);

		List<Role> roleList = roleRepository.findAll();
		model.addAttribute("roleList", roleList);
		return "redirect:/role/getRole";
	}

	@RequestMapping("/updateForm/{id}")
	public String updateRole(@PathVariable int id, Model model) {
		Optional<Role> role = roleService.findById(id);
		if (role.isPresent()) {
			model.addAttribute("roleTemp", role.get());
		}
		return "role/Update";
	}

	@PostMapping("/update")
	public String doUpdateRole(Model model, @ModelAttribute("Role") Role role) {

		roleRepository.save(role);

		model.addAttribute("roleList", roleRepository.findAll());
		
		return "redirect:/role/getRole";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRole(Model model, @PathVariable("id") Integer id, @ModelAttribute("Role") Role role) {
		Role roleTemp = roleRepository.getById(id);

		roleRepository.delete(roleTemp);

		List<Role> roleList = roleRepository.findAll();
		model.addAttribute("roleList", roleList);
		return "redirect:/role/getRole";
	}

}
