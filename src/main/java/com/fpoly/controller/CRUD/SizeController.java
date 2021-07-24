package com.fpoly.controller.CRUD;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
		
		List<Size> size = sizeRepository.findByValue(id);
		if (size!=null) {
			
			for (int i = 0; i < 1; i++) {
				model.addAttribute("size", size.get(i));
				System.out.println(size.get(i));
			}
			model.addAttribute("sizeOld", id);
		}

		List<Product_Detail> productDetail = product_DetailRepository.findAll();

		model.addAttribute("productDetail", productDetail);

		return "size/update";
	}

	@PostMapping("/update/{id}")
	public String doUpdatepSize(@PathVariable int id,Model model, @ModelAttribute("Size") Size size ,RedirectAttributes attributes) {

		List<Size> sizeTemp = sizeRepository.findByValue(Integer.parseInt(size.getValue()));

		List<Size> sizeOld = sizeRepository.findByValue(id);
		
		if(sizeTemp.isEmpty()) {
			
			for (Size size2 : sizeOld) {
				size2.setValue(size.getValue());
				System.out.println(size.getValue());
				sizeRepository.save(size2);
				attributes.addFlashAttribute("Valid", true);
			}
			
		}else {
			attributes.addFlashAttribute("notValid", true);
			return "redirect:/productDetail/getProductDetail";
		}

		// List<Address> addressList = addressRepos.findAll();

		return "redirect:/productDetail/getProductDetail";

	}
	@RequestMapping("/delete/{id}")
	public String deleteSize(Model model , @PathVariable("id") Integer id ,  @ModelAttribute("Size") Size size ,RedirectAttributes attributes) {
		
		List<Size> size1 = sizeRepository.findByValue(id);
		for (Size size2 : size1) {
			System.out.println(size1);
			sizeRepository.delete(size2);
		}
		attributes.addFlashAttribute("DeleteSuccess", true);
		
		return "redirect:/productDetail/getProductDetail";
	}
	
}
