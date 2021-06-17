package com.fpoly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.model.Size;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.SizeRepository;
import com.fpoly.service.SizeService;

@Controller
@RequestMapping("/size")
public class SizeController {

	@Autowired
	SizeRepository sizeRepository;

	@Autowired
	SizeService sizeService;

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@RequestMapping("/get")
	public String getAll(Model model) {

		List<Size> size = sizeRepository.findAll();

		List<Product_Detail> productDetail = product_DetailRepository.findAll();

		model.addAttribute("size", size);

		model.addAttribute("productDetail", productDetail);

		return "size/show";

	}

	@RequestMapping("/save")
	public String saveSize(Model model, @ModelAttribute("Size") Size size) {

		sizeRepository.save(size);

		return "redirect:/size/get";

	}
	
	@RequestMapping("/update/{id}")
	public String updateSize(@PathVariable int id, Model model) {
		
		Optional<Size> size = sizeRepository.findById(id);

		if (size.isPresent()) {
			model.addAttribute("size", size.get());
		}

		List<Product_Detail> productDetail = product_DetailRepository.findAll();

		model.addAttribute("productDetail", productDetail);

		return "size/update";
	}

	@PostMapping("/update")
	public String doUpdatepSize(Model model, @ModelAttribute("Size") Size size) {

		sizeRepository.save(size);

		// List<Address> addressList = addressRepos.findAll();

		return "redirect:/size/get";

	}
	@RequestMapping("/delete/{id}")
	public String deleteSize(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Size") Size size) {
		
		Size size1 = sizeRepository.getById(id);
		
		sizeRepository.delete(size1);
		
		return "redirect:/size/get";
	}
	
}
