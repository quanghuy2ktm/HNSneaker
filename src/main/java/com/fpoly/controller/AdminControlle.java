package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.repositories.BrandRepository;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.Product_ImgRepository;
import com.fpoly.repositories.SizeRepository;
import com.fpoly.service.Product_DetailService;

@Controller
@RequestMapping("admin")
public class AdminControlle {

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@Autowired
	Product_DetailService product_DetailService;

	@Autowired
	ProductRepository productRepos;
	
	@Autowired
	Product_ImgRepository prodimgRepo;
	
	@Autowired
	SizeRepository sizeRepo;
	
	@Autowired
	BrandRepository brandRepo;
	
	@Autowired
	ColorRepository colorRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@RequestMapping(value = "")
	public String getAll1(Model model) {
		List<Product_Detail> list = product_DetailRepository.findAll();
		model.addAttribute("list", list);
		
		List<Product> productList = productRepos.findAll();
		model.addAttribute("productList", productList);
		
		List<Product_Img> prodImg = prodimgRepo.findAll();
		model.addAttribute("prodImg", prodImg);
		
		List<String> sizeList = sizeRepo.findAllSizes();
		model.addAttribute("sizeList", sizeList);
		
		List<String> brandList = brandRepo.findAllBrand();
		model.addAttribute("brandList", brandList);
		
		List<String> colorList = colorRepo.findAllColor();
		model.addAttribute("colorList", colorList);
		
		List<String> categoryList = categoryRepo.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		
		return "admin/show";
	}
}
