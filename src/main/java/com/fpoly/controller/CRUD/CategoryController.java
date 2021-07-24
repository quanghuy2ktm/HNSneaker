package com.fpoly.controller.CRUD;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.model.Category;
import com.fpoly.model.Role;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	public String categoryOLD=null;
	
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
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date listingDate = new Date();
		System.out.println(dateFormat.format(listingDate));
		category.setCategoryListingDate(listingDate);
		categoryRepository.save(category);

		
		return "redirect:/category/get";
	}

	@RequestMapping("/update/{id}")
	public String updateRole(@PathVariable String id, Model model) {
		System.out.println("gtri:"+id);
		List<Category> category = categoryRepository.findByCategoryName(id);
		
		if (category!=null) {
			for (int i = 0; i < 1; i++) {
				model.addAttribute("category", category.get(i));
				System.out.println(category.get(i));
			}
			model.addAttribute("cateOld", id);
			categoryOLD = id;
		}
		return "category/Update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String doUpdateRole(Model model, @ModelAttribute("Category") Category category,RedirectAttributes attributes) {
		
		List<Category> categoryTemp = categoryRepository.findByCategoryName(category.getCategoryName());

		List<Category> categoryOld = categoryRepository.findByCategoryName(categoryOLD);
		
		if(categoryTemp.isEmpty()) {
			for (Category category2 : categoryOld) {
				category2.setCategoryName(category.getCategoryName());
				Date listingDate = new Date();
				category2.setCategoryListingDate(listingDate);
				categoryRepository.save(category2);
				categoryOLD = null;
				attributes.addFlashAttribute("Valid", true);
			}
		}else {
			
			  System.out.println(categoryOLD); 
			  attributes.addFlashAttribute("notValid",true); 
			  return "redirect:/productDetail/getProductDetail";
			 
		}
		
		model.addAttribute("category",categoryRepository.findAll());
		
		return "redirect:/productDetail/getProductDetail";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRole(Model model, @PathVariable("id") String id, @ModelAttribute("Category") Category Category ,RedirectAttributes attributes) {
		
		List<Category> categoryTemp = categoryRepository.findByCategoryName(id);
		System.out.println(id);
		for (Category category2 : categoryTemp) {
			System.out.println("Xóa: "+category2);
			categoryRepository.delete(category2);
		}
		 attributes.addFlashAttribute("DeleteSuccess",true); 
		return "redirect:/productDetail/getProductDetail";
	}

	
	
	
}
