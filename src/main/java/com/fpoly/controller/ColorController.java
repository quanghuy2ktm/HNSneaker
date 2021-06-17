package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Color;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.service.ColorService;

@Controller
@RequestMapping("/color")

public class ColorController {
	
	@Autowired 
	ColorRepository colorRepository;
	
	@Autowired
	ColorService colorService;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Color> color = colorRepository.findAll();
		
		model.addAttribute("color",color);
		return "color/show";
		
	}
	
		@RequestMapping("/save")
		public String saveColor(Model model, @ModelAttribute("Color")Color color) {
			colorRepository.save(color);
			return "redirect:/color/get";
		}
	
}
