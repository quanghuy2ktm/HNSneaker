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

import com.fpoly.model.Category;
import com.fpoly.model.Role;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Category> category = categoryRepository.findAll();
		
		model.addAttribute("category" , category);
		
		return "category/show";
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCategory(Model model, @ModelAttribute("Category") Category category) {
		
		categoryRepository.save(category);

		
		return "redirect:/category/get";
	}

	@RequestMapping("/update/{id}")
	public String updateRole(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.findById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
		}
		return "category/Update";
	}

	@PostMapping("/update")
	public String doUpdateRole(Model model, @ModelAttribute("Category") Category category) {

		categoryRepository.save(category);
		
		model.addAttribute("category",categoryRepository.findAll());
		
		return "redirect:/category/get";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRole(Model model, @PathVariable("id") Integer id, @ModelAttribute("Category") Category Category) {
		Category category1 = categoryRepository.getById(id);

		categoryRepository.delete(category1);

		
		return "redirect:/category/get";
	}

	
	
	
}
