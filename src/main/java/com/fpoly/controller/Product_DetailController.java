package com.fpoly.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.Product_DetailService;
//Author: Quang Huy - 17/06
@Controller
@RequestMapping("/productDetail")
public class Product_DetailController {

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@Autowired
	Product_DetailService product_DetailService;

	@Autowired
	ProductRepository productRepos;
	
	@RequestMapping(value = "/getProdcutDetail")
	public String getAll(Model model) {
		List<Product_Detail> list = product_DetailRepository.findAll();
		model.addAttribute("list", list);
		List<Product> productList = productRepos.findAll();
		model.addAttribute("productList", productList);
		
		return "proDetail/show";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("Product_Detail") Product_Detail product_Detail) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date listingDate = new Date();
		System.out.println(dateFormat.format(listingDate));
		product_Detail.setListingDate(listingDate);
		product_DetailRepository.save(product_Detail);

		return "redirect:/productDetail/getProdcutDetail";
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String doUpdateProduct(Model model, @ModelAttribute("Product_Detail") Product_Detail product_Detail) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date listingDate = new Date();
		System.out.println(dateFormat.format(listingDate));
		
		product_Detail.setListingDate(listingDate);
		
		product_DetailRepository.save(product_Detail);

		return "redirect:/productDetail/getProdcutDetail";

	}
	
	@RequestMapping("/updateForm/{id}")
	public String updateProductDetail(@PathVariable("id") int id, Model model) {
		Optional<Product_Detail> productDetail = product_DetailRepository.findById(id);
		List<Product> productList = productRepos.findAll();
		model.addAttribute("productList", productList);
		if (productDetail.isPresent()) {
			model.addAttribute("ProductDetail", productDetail.get());
		}
		return "proDetail/Update";
	}
	
	
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("Product_Detail") Product_Detail product_Detail) {
		
		Product_Detail detail = product_DetailRepository.getById(id);

		product_DetailRepository.delete(detail);

	
		return "redirect:/productDetail/getProdcutDetail";
	}
}
