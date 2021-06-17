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
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.model.Address;
import com.fpoly.repositories.AddressRepository;
import com.fpoly.service.AddressService;

@Controller
@RequestMapping("/address/")
public class AddressController {

	@Autowired
	AddressRepository addressRepos;

	@Autowired
	AddressService addService;

	@RequestMapping(value = "/getAddress", method = RequestMethod.GET)
	public String getAllAddress(Model model) {
		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "address/AddressIndex";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAddress(Model model, @ModelAttribute("Address") Address address) {
		addressRepos.save(address);

		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "redirect:/address/getAddress";
	}

	@RequestMapping("/updateForm/{id}")
	public String updateAddress(@PathVariable int id, Model model) {
		Optional<Address> address = addService.findById(id);
		if (address.isPresent()) {
			model.addAttribute("addressTemp", address.get());
		}
		return "address/Update";
	}

	@PostMapping("/update")
	public String doUpdateAddress(Model model, @ModelAttribute("Address") Address address) {
		
		addressRepos.save(address);

		//List<Address> addressList = addressRepos.findAll();

		model.addAttribute("addressList", addressRepos.findAll());

		return "redirect:/address/getAddress";

	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String DeleteAddress(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("Address") Address address) {
		Address addressTemp = addressRepos.getById(id);

		addressRepos.delete(addressTemp);

		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "redirect:/address/getAddress";
	}

}
