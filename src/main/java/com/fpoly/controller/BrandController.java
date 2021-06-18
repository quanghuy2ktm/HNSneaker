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

import com.fpoly.model.Brand;
import com.fpoly.model.Color;
import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Size;
import com.fpoly.repositories.BrandRepository;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.service.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	BrandService brandService;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/get")
	public String getAll(Model model) {

		List<Product> product = productRepository.findAll();

		model.addAttribute("product", product);
		
		List<Brand> brand = brandRepository.findAll();

		model.addAttribute("brand", brand);

		return "brand/show";

	}

	@RequestMapping("/save")
	public String saveBrand(Model model, @ModelAttribute("Brand") Brand brand) {
		List<Product> product = productRepository.findAll();

		model.addAttribute("product", product);

		brandRepository.save(brand);

		return "redirect:/brand/get";
	}

	@RequestMapping("/update/{id}")
	public String updateBrand(@PathVariable int id, Model model) {

		List<Product> product = productRepository.findAll();

		model.addAttribute("product", product);

		Optional<Brand> brand = brandRepository.findById(id);

		if (brand.isPresent()) {
			model.addAttribute("brand", brand.get());
		}

		return "brand/update";
	}

	@PostMapping("/update")
	public String doUpdatepSize(Model model, @ModelAttribute("Brand") Brand brand) {

		brandRepository.save(brand);

		return "redirect:/brand/get";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteBrand(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Brand") Brand Brand) {
		
		Brand color1 = brandRepository.getById(id);
		
		brandRepository.delete(color1);
		
		return "redirect:/brand/get";
	}
	
}
