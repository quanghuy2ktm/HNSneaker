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
	public String updateBrand(@PathVariable String id, Model model) {
		System.out.println(id);
		List<Product> product = productRepository.findAll();

		model.addAttribute("product", product);
		
		List<Brand> brand = brandRepository.findByBrand(id);

		if (brand!=null) {
			for (int i = 0; i < 1; i++) {
				model.addAttribute("brand", brand.get(i));
			}
			
			model.addAttribute("brandOld", id);
		}

		return "brand/update";
	}

	@PostMapping("/update/{brandOld}")
	public String doUpdatepSize(@PathVariable String brandOld,Model model, @ModelAttribute("Brand") Brand brand, RedirectAttributes attributes) {

		List<Brand> brandOldlist = brandRepository.findByBrand(brandOld);
		
		List<Brand> brandTemp = brandRepository.findByBrand(brand.getName());
		
		if(brandTemp.isEmpty()) {
			for (Brand brand2 : brandOldlist) {
				brand2.setName(brand.getName());
				System.out.println(brand.getName());
				brandRepository.save(brand2);
				attributes.addFlashAttribute("Valid", true);
			}
		}else {
			attributes.addFlashAttribute("notValid", true);
			return "redirect:/productDetail/getProductDetail";
		}
		
		brandRepository.save(brand);

		return "redirect:/productDetail/getProductDetail";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteBrand(Model model , @PathVariable("id") String id ,  @ModelAttribute("Brand") Brand Brand,RedirectAttributes attributes) {
		
		List<Brand> brandTemp = brandRepository.findByBrand(id);
		for (Brand brands : brandTemp) {
			System.out.println(brandTemp);
			brandRepository.delete(brands);
		}
		 attributes.addFlashAttribute("DeleteSuccess",true); 
		return "redirect:/productDetail/getProductDetail";
	}
	
}
