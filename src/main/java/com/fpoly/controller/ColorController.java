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

import com.fpoly.model.Color;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.ColorService;

@Controller
@RequestMapping("/color/")

public class ColorController {
	
	@Autowired 
	ColorRepository colorRepository;
	
	@Autowired
	ColorService colorService;
	
	@Autowired
	Product_DetailRepository product_DetailRepository;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Product_Detail> productDetail = product_DetailRepository.findAll();

		model.addAttribute("productDetail", productDetail);
		
		List<Color> color = colorRepository.findAll();
		
		model.addAttribute("color",color);
		return "color/show";
		
	}
	
		@RequestMapping("/save")
		public String saveColor(Model model, @ModelAttribute("Color")Color color) {
			List<Product_Detail> productDetail = product_DetailRepository.findAll();

			model.addAttribute("productDetail", productDetail);
			colorRepository.save(color);
			
			return "redirect:/color/get";
		}
		
		
		@RequestMapping("/update/{id}")
		public String updateColor(@PathVariable int id, Model model) {
			
			List<Product_Detail> productDetail = product_DetailRepository.findAll();

			model.addAttribute("productDetail", productDetail);
			
			Optional<Color> color = colorRepository.findById(id);

			if (color.isPresent()) {
				model.addAttribute("color", color.get());
			}

			
			return "color/update";
		}

		@PostMapping("/update")
		public String doUpdatepImg(Model model, @ModelAttribute("Color") Color color) {

			colorRepository.save(color);

			return "redirect:/color/get";

		}
		
		
		@RequestMapping("/delete/{id}")
		public String deleteColor(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Color") Color color) {
			
			Color color1 = colorRepository.getById(id);
			
			colorRepository.delete(color1);
			
			return "redirect:/color/get";
		}
		
		
		
	
}
