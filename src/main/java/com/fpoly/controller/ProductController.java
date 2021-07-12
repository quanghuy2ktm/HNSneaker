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

import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

	
	@Autowired
	Product_DetailRepository productDetailRepos;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Product> product = productRepository.findAll();
		
		List<Product_Detail> pd = productDetailRepos.findAll();
		
		for(int i =0; i<product.size();i++) {
			System.out.println(product.get(i).getProductDetail());
		}
		
		model.addAttribute("productDetail", pd);
		
		model.addAttribute("product" , product);
		
		return "product/show";
		
	}
	
	@RequestMapping("/save")
	public String saveProduct(Model model , @ModelAttribute("Product") Product product) {
		
		productRepository.save(product);
		
		return "redirect:/product/get";
		
	}
	
	@RequestMapping("/updateForm/{id}")
	public String updateProduct(@PathVariable("id") int id, Model model) {
		Optional<Product> product = productService.findById(id);
		List<Product_Detail> pd = productDetailRepos.findAll();
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		}
		model.addAttribute("productDetail", pd);
		return "product/Update";
	}

	@PostMapping("/update")
	public String doUpdateProduct(Model model, @ModelAttribute("Product") Product product) {
		
		productRepository.save(product);

		return "redirect:/product/get";

	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Product") Product product) {
		
		Product product1 = productRepository.getById(id);
		
		productRepository.delete(product1);
		
		return "redirect:/product/get";
	}
}
