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
import com.fpoly.model.User_Role;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Product> product = productRepository.findAll();
		
		model.addAttribute("product" , product);
		
		return "product/show";
		
	}
	
	@RequestMapping("/save")
	public String saveProduct(Model model , @ModelAttribute("Product") Product product) {
		
		productRepository.save(product);
		
		return "redirect:/product/get";
		
	}
	
	@RequestMapping("/updateForm/{id}")
	public String updateProduct(@PathVariable int id, Model model) {
		Optional<Product> product = productService.findById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		}
		return "product/Update";
	}

	@PostMapping("/update")
	public String doUpdateProduct(Model model, @ModelAttribute("Product") Product product) {
		
		productRepository.save(product);

		//List<Address> addressList = addressRepos.findAll();

		return "redirect:/product/get";

	}
	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Product") Product product) {
		
		Product product1 = productRepository.getById(id);
		
		productRepository.delete(product1);
		
		return "redirect:/product/get";
	}
}
