package com.fpoly.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpoly.DTO.ArticleFilterForm;
import com.fpoly.model.Product_Detail;
import com.fpoly.repositories.BrandRepository;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.Product_ImgRepository;
import com.fpoly.repositories.SizeRepository;
import com.fpoly.service.ArticleService;
import com.fpoly.service.SortFilter;


@Controller
public class StoreController {
	
	@Autowired
    ArticleService articleService;
	
	@Autowired
	Product_DetailRepository pdrepos;
	
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
	
	@RequestMapping("store")
	public String store(@ModelAttribute("filters") ArticleFilterForm filters, Model model, Authentication aut) {
		
		Integer page = filters.getPage();			
		
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		
		Page<Product_Detail> pageresult = articleService.findArticlesByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getBrand(), filters.getSearch());	
//		List<Product_Img> prodImg = prodimgRepo.findAll();
//		model.addAttribute("prodImg", prodImg);
		
		List<String> sizeList = sizeRepo.findAllSizes();
		model.addAttribute("allSizes", sizeList);
		
		List<String> brandList = brandRepo.findAllBrand();
		model.addAttribute("allBrands", brandList);
		
//		List<String> colorList = colorRepo.findAllColor();
//		model.addAttribute("colorList", colorList);
		
		List<String> categoryList = categoryRepo.findAllCategories();
		model.addAttribute("allCategories", categoryList);
		
		model.addAttribute("articles", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
//		return "proDetail/store";
		return "proDetail/store";
		
		
	}
	
	@RequestMapping("/article-detail")
	public String articleDetail(@PathParam("id") Integer id, Model model, Authentication aut) {
		Product_Detail article = pdrepos.findByIdp(id);
		model.addAttribute("article", article);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addArticleSuccess", model.asMap().get("addArticleSuccess"));
		return "proDetail/articleDetail";
	}
}
