package com.fpoly.controller.CRUD;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.model.Color;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.model.Size;
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
		public String updateColor(@PathVariable String id, Model model) {
			
			List<Product_Detail> productDetail = product_DetailRepository.findAll();

			model.addAttribute("productDetail", productDetail);
			
			List<Color> color = colorRepository.findByColor(id);

			if (color!=null) {
				for (int i = 0; i < 1; i++) {
					model.addAttribute("color", color.get(i));
					System.out.println("----"+color);
				}
				model.addAttribute("colorOld", id);
			}

			
			return "color/update";
		}

		@PostMapping("/update/{id}")
		public String doUpdatepImg(@PathVariable String id, Model model, @ModelAttribute("Color") Color color,RedirectAttributes attributes) {
			
			List<Color> colorTemp = colorRepository.findByColor(color.getColorName());

			List<Color> colorOld = colorRepository.findByColor(id);
			
			if(colorTemp.isEmpty()) {
				
				for (Color color2 : colorOld) {
					color2.setColorName(color.getColorName());
					colorRepository.save(color2);
					attributes.addFlashAttribute("Valid", true);
				}
				
			}else {
				attributes.addFlashAttribute("notValid", true);
				return "redirect:/productDetail/getProductDetail";
			}
			

			return "redirect:/productDetail/getProductDetail";

		}
		
		
		@RequestMapping("/delete/{id}")
		public String deleteColor(Model model , @PathVariable("id") String id ,  @ModelAttribute("Color") Color color,RedirectAttributes attributes ) {
			
			List<Color> colorOld = colorRepository.findByColor(id);
			for (Color color2 : colorOld) {
				colorRepository.delete(color2);
			}
			attributes.addFlashAttribute("DeleteSuccess", true);
			
			
			return "redirect:/productDetail/getProductDetail";
		}
		
		
		
	
}
