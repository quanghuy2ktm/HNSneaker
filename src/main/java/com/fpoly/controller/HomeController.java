package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.Product_DetailService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@Autowired
	Product_DetailService product_DetailService;

	@Autowired
	ProductRepository productRepos;

	@RequestMapping(value = "")
	public String getAll(Model model) {
		List<Product_Detail> list = product_DetailRepository.findAll();
		model.addAttribute("list", list);
		List<Product> productList = productRepos.findAll();
		model.addAttribute("productList", productList);

		return "index";
	}

}
