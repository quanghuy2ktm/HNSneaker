package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.Product_ImgRepository;
import com.fpoly.service.Product_ImgService;

@Controller
@RequestMapping("productImg")
public class Product_ImgController {

	@Autowired
	Product_ImgRepository product_ImgRepository;
	
	@Autowired
	Product_ImgService product_ImgService;
	
	@Autowired
	Product_DetailRepository product_DetailRepository;
	
	@RequestMapping("/get")
	public String getAll(Model model) {
		
		List<Product_Img> product_Img = product_ImgRepository.findAll();
		
		List<Product_Detail> proDetail = product_DetailRepository.findAll();
		
		model.addAttribute("productDeltai" , proDetail);
		
		model.addAttribute("product_Img" , product_Img);
		
		return "productImg/show";
		
	}
	
	@RequestMapping("/save")
	public String saveProduct_Img(Model model , @ModelAttribute("Product_Img") Product_Img product_Img) {
		
		product_ImgRepository.save(product_Img);
		
		return "redirect:/productImg/get";
		
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct_Img(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Product_Img") Product_Img product_Img) {
		
		Product_Img producImg = product_ImgRepository.getById(id);
		
		product_ImgRepository.delete(producImg);
		
		return "redirect:/productImg/get";
	}
}
