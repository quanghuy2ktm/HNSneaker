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

import com.fpoly.model.Category;
import com.fpoly.model.Category_Product;
import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Size;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.repositories.Category_ProductRepository;
import com.fpoly.repositories.ProductRepository;

@Controller
@RequestMapping("/categoryProduct")
public class Category_ProductController {

	@Autowired
	Category_ProductRepository category_ProductRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping("/get")
	public String getAll(Model model) {

		List<Category_Product> cateProduct = category_ProductRepository.findAll();

		List<Category> category = categoryRepository.findAll();

		List<Product> product = productRepository.findAll();

		model.addAttribute("cateProduct", cateProduct);

		model.addAttribute("category", category);

		model.addAttribute("product", product);

		return "categoryProduct/show";

	}

	@RequestMapping("/save")
	public String saveCateProduct(Model model, @ModelAttribute("Category_Product") Category_Product category_Product) {

		category_ProductRepository.save(category_Product);

		return "redirect:/categoryProduct/get";

	}

	@RequestMapping("/update/{id}")
	public String updateSize(@PathVariable int id, Model model) {

		Optional<Category_Product> cateProduct = category_ProductRepository.findById(id);

		if (cateProduct.isPresent()) {
			model.addAttribute("cateProduct", cateProduct.get());
		}

		List<Category> category = categoryRepository.findAll();

		List<Product> product = productRepository.findAll();

		model.addAttribute("category", category);

		model.addAttribute("product", product);
		

		return "categoryProduct/update";
	}

	@PostMapping("/update")
	public String doUpdatepCaPro(Model model, @ModelAttribute("Category_Product") Category_Product category_Product) {

		category_ProductRepository.save(category_Product);

		// List<Address> addressList = addressRepos.findAll();

		return "redirect:/categoryProduct/get";

	}

	@RequestMapping("/delete/{id}")
	public String deleteCaPro(Model model, @PathVariable("id") Integer id, @ModelAttribute("Category_Product") Category_Product category_Product) {

		Category_Product category_Product2 = category_ProductRepository.getById(id);
		
		category_ProductRepository.delete(category_Product2);

		return "redirect:/categoryProduct/get";
	}
}
