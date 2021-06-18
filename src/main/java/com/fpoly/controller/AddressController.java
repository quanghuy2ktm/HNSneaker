package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.model.Address;
import com.fpoly.repositories.AddressRepository;

@Controller
@RequestMapping("/address/")
public class AddressController {

	@Autowired
	AddressRepository addressRepos;
	
	@RequestMapping(value="/getAddress", method=RequestMethod.GET)
	public String getAllAddress(Model model) {
		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "address/AddressIndex";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveAddress(Model model, @ModelAttribute("Address")Address address) {
		addressRepos.save(address);
		
		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "redirect:/address/getAddress";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String UpdateAddress(Model model, @ModelAttribute("Address")Address address) {
		
		System.out.println("id:"+address.getIDAdress());
		
		addressRepos.save(address);
		
		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "redirect:/address/getAddress";
	}
	
	@RequestMapping(value="/updateForm/{id}", method=RequestMethod.GET)
	public String UpdateAddressPage(Model model,@PathVariable("id") Integer id, @ModelAttribute("Address")Address address) {
		Address addressTemp = addressRepos.getOne(id);
		System.out.println(addressTemp.getReciverName());
		model.addAttribute("addressTemp", addressTemp);
		return "address/Update";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String DeleteAddress(Model model,@PathVariable("id") Integer id, @ModelAttribute("Address")Address address) {
		Address addressTemp = addressRepos.getById(id);
		
		addressRepos.delete(addressTemp);
		
		List<Address> addressList = addressRepos.findAll();
		model.addAttribute("addressList", addressList);
		return "redirect:/address/getAddress";
	}
	
}
