package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.model.Product_Detail;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.Product_DetailService;

@Controller
@RequestMapping("productDetail")
public class Product_DetailController {

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@Autowired
	Product_DetailService product_DetailService;

	@RequestMapping(value = "/getProdcutDetail")
	public String getAll(Model model) {
		List<Product_Detail> list = product_DetailRepository.findAll();
		model.addAttribute("list", list);
		return "proDetail/show";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRole(Model model, @ModelAttribute("Product_Detail") Product_Detail product_Detail) {

		product_DetailRepository.save(product_Detail);

		return "redirect:/productDetail/getProdcutDetail";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteRole(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("Product_Detail") Product_Detail product_Detail) {
		
		Product_Detail detail = product_DetailRepository.getById(id);

		product_DetailRepository.delete(detail);

	
		return "redirect:/productDetail/getProdcutDetail";
	}
}
