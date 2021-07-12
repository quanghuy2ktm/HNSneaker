package com.fpoly.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.DTO.ProductDetailDTO;
import com.fpoly.model.Address;
import com.fpoly.model.Brand;
import com.fpoly.model.Category;
import com.fpoly.model.Color;
import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.model.Size;
import com.fpoly.repositories.BrandRepository;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.Product_ImgRepository;
import com.fpoly.repositories.SizeRepository;
import com.fpoly.service.Product_DetailService;

@Controller
@RequestMapping("/productDetail")
public class Product_DetailController {

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
	
	@RequestMapping(value = "/getProductDetail")
	public String getAll(Model model) {
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
		
		return "proDetail/show";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("Product_Detail") Product_Detail product_Detail, HttpServletRequest request) {
		
		Date listingDate = new Date();
		
		Product_Detail producDetailTemp = new ProductDetailDTO()
				.withTitle(product_Detail.getTitle())
				.stockAvailable(product_Detail.getStock())
				.withPrice(product_Detail.getPrice())
				.imageLink(Arrays.asList(request.getParameter("prodImg").split("\\s*,\\s*")))
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("categories").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brands").split("\\s*,\\s*")))
				.color(Arrays.asList(request.getParameter("colors").split("\\s*,\\s*")))
				.build();	
		
		
		//Set các trường bthg
		
		producDetailTemp.setListingDate(listingDate);
		producDetailTemp.setShortTitle(product_Detail.getShortTitle());
		producDetailTemp.setDescription(product_Detail.getDescription());
		producDetailTemp.setProductStatus(true);
		producDetailTemp.setSku(product_Detail.getSku());
		producDetailTemp.setMaterials(product_Detail.getMaterials());
		
		product_DetailRepository.save(producDetailTemp);
		System.out.println("test: "+producDetailTemp.getIDProductDetail());
		Integer id = producDetailTemp.getIDProductDetail();
		
		//Set size, category, brand vào list.
		Product_Detail pdtemp = new Product_Detail();
		pdtemp = product_DetailRepository.getById(id);
		
		System.out.println("size đối chiếu "+pdtemp.getSize());
		System.out.println("Category đối chiếu "+pdtemp.getCategory());
		System.out.println("brand đối chiếu "+pdtemp.getBrand());
		System.out.println("color đối chiếu "+pdtemp.getColor());
		System.out.println("img đối chiếu "+pdtemp.getProductImg());
		
		
		//Size
//		String[] sizeArrays = request.getParameter("size").split("\\s*,\\s*");
//		for (int i = 0; i < sizeArrays.length; i++) {
//			System.out.println("- Size value: "+sizeArrays[i]);
//			
//			if(pdtemp.getSize().toString().trim().equals(sizeArrays[i].trim())==true) {
//				Size sizeval = new Size();
//				sizeval.setValue(sizeArrays[i].trim());
//				sizeval.setProductDetail(pdtemp);
//				sizeRepo.save(sizeval);
//			}
//		}	
//		//Category
//		String[] categoryArrays = request.getParameter("categories").split("\\s*,\\s*");
//		
//		
//		for (int i = 0; i < categoryArrays.length; i++) {
//			System.out.println("- categories value: "+categoryArrays[i]);
//			if(pdtemp.getCategory().toString().trim().equals(categoryArrays[i].trim())==true) {
//				Category cateVal = new Category();
//				cateVal.setCategoryName(categoryArrays[i].trim());
//				cateVal.setCategoryListingDate(listingDate);
//				cateVal.setCategoryStatus(true);
//				cateVal.setProductDetail(pdtemp);
//				categoryRepo.save(cateVal);
//			}
//		}
//		// Brand
//		String[] brandArrays = request.getParameter("brands").split("\\s*,\\s*");
//		for (int i = 0; i < brandArrays.length; i++) {
//			System.out.println("- brand value: "+brandArrays[i]);
//			if(pdtemp.getBrand().toString().trim().equals(brandArrays[i].trim())==false) {
//				Brand brandVal = new Brand();
//				brandVal.setName(brandArrays[i].trim());
//				brandVal.setProductDetail(pdtemp);
//				brandRepo.save(brandVal);
//			}
//		}
//		//Color
//		String[] colorArrays = request.getParameter("colors").split("\\s*,\\s*");
//		for (int i = 0; i < colorArrays.length; i++) {
//			System.out.println("- color value: "+colorArrays[i] + colorArrays.length);
//			if(pdtemp.getColor().toString().trim().equals(colorArrays[i].trim())==false) {
//				Color colorVal = new Color();
//				colorVal.setColorName(colorArrays[i].trim());
//				colorVal.setProductDetail(pdtemp);
//				colorRepo.save(colorVal);
//			}
//		}
//		
//		//prodImg
//		String[] ImageArrays = request.getParameter("prodImg").split("\\s*,\\s*");
//		for (int i = 0; i < ImageArrays.length; i++) {
//			System.out.println("- image value: "+ImageArrays[i]);
//			if(pdtemp.getProductImg().toString().trim().equals(ImageArrays[i].trim())==false) {
//				Product_Img imgVal = new Product_Img();
//				imgVal.setPicture1(ImageArrays[i].trim());
//				imgVal.setProductDeltai(pdtemp);
//				prodimgRepo.save(imgVal);
//			}
//		}
		
		
		producDetailTemp = null;
		return "redirect:/admin";
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String doUpdateProduct(Model model, @ModelAttribute("Product_Detail") Product_Detail product_Detail, HttpServletRequest request) {
		
		Date listingDate = new Date();
		
		Product_Detail producDetailTemp = new ProductDetailDTO()
				.withTitle(product_Detail.getTitle())
				.stockAvailable(product_Detail.getStock())
				.withPrice(product_Detail.getPrice())
				.imageLink(Arrays.asList(request.getParameter("prodImg").split("\\s*,\\s*")))
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(Arrays.asList(request.getParameter("categories").split("\\s*,\\s*")))
				.ofBrand(Arrays.asList(request.getParameter("brands").split("\\s*,\\s*")))
				.color(Arrays.asList(request.getParameter("colors").split("\\s*,\\s*")))
				.build();	
		
		
		//Set các trường bthg
		producDetailTemp.setIDProductDetail(product_Detail.getIDProductDetail());
		
		producDetailTemp.setListingDate(listingDate);
		producDetailTemp.setShortTitle(product_Detail.getShortTitle());
		producDetailTemp.setDescription(product_Detail.getDescription());
		producDetailTemp.setProductStatus(product_Detail.isProductStatus());
		producDetailTemp.setSku(product_Detail.getSku());
		producDetailTemp.setMaterials(product_Detail.getMaterials());
		
		product_DetailRepository.save(producDetailTemp);
		
		System.out.println("test: "+producDetailTemp.getIDProductDetail());
		Integer id = producDetailTemp.getIDProductDetail();
		//Set size, category, brand vào list.
		Product_Detail pdtemp = new Product_Detail();
		pdtemp = product_DetailRepository.getById(id);
		
		//Size
//		String[] sizeArrays = request.getParameter("size").split("\\s*,\\s*");
//		for (int i = 0; i < sizeArrays.length; i++) {
//			System.out.println("- Size value: "+sizeArrays[i]);
//			
//			if(pdtemp.getSize().toString().trim().equalsIgnoreCase(sizeArrays[i].trim())==false) {
//				Size sizeval = new Size();
//				sizeval.setValue(sizeArrays[i].trim());
//				sizeval.setProductDetail(pdtemp);
//				sizeRepo.save(sizeval);
//			}
//		}	
//		//Category
//		String[] categoryArrays = request.getParameter("categories").split("\\s*,\\s*");
//		for (int i = 0; i < categoryArrays.length; i++) {
//			System.out.println("- categories value: "+categoryArrays[i]);
//			
//			if(pdtemp.getCategory().toString().trim().equalsIgnoreCase(categoryArrays[i].trim())==false) {
//				Category cateVal = new Category();
//				cateVal.setCategoryName(categoryArrays[i].trim());
//				cateVal.setCategoryListingDate(listingDate);
//				cateVal.setCategoryStatus(true);
//				cateVal.setProductDetail(pdtemp);
//				categoryRepo.save(cateVal);
//			}
//			
//		}
//		// Brand
//		String[] brandArrays = request.getParameter("brands").split("\\s*,\\s*");
//		for (int i = 0; i < brandArrays.length; i++) {
//			System.out.println("- brand value: "+brandArrays[i]);
//			if(pdtemp.getBrand().toString().trim().equalsIgnoreCase(brandArrays[i].trim())==false) {
//				Brand brandVal = new Brand();
//				brandVal.setName(brandArrays[i].trim());
//				brandVal.setProductDetail(pdtemp);
//				brandRepo.save(brandVal);
//			}
//		}
//		//Color
//		String[] colorArrays = request.getParameter("colors").split("\\s*,\\s*");
//		for (int i = 0; i < colorArrays.length; i++) {
//			System.out.println("- color value: "+colorArrays[i]);
//			if(pdtemp.getColor().toString().trim().equalsIgnoreCase(colorArrays[i].trim())==false) {
//				Color colorVal = new Color();
//				colorVal.setColorName(colorArrays[i].trim());
//				colorVal.setProductDetail(pdtemp);
//				colorRepo.save(colorVal);
//			}
//		}
//		
//		//prodImg
//		String[] ImageArrays = request.getParameter("prodImg").split("\\s*,\\s*");
//		for (int i = 0; i < ImageArrays.length; i++) {
//			System.out.println("- image value: "+ImageArrays[i] + ImageArrays.length);
//			if(pdtemp.getProductImg().toString().trim().equalsIgnoreCase(ImageArrays[i].trim())==false) {
//				Product_Img imgVal = new Product_Img();
//				imgVal.setPicture1(ImageArrays[i].trim());
//				imgVal.setProductDeltai(pdtemp);
//				prodimgRepo.save(imgVal);
//			}
//		}
		
		
		producDetailTemp = null;
		return "redirect:/admin";

	}
	
	@RequestMapping("/updateForm/{id}")
	public String updateProductDetail(@PathVariable("id") int id, Model model) {
		Optional<Product_Detail> productDetail = product_DetailRepository.findById(id);
		Product_Detail prod = product_DetailRepository.getById(id);
		
		String preselectedSizes = "";
		for (Size size : prod.getSize()) {
			preselectedSizes += (size.getValue() + ",");
		}
		String preselectedBrands = "";
		for (Brand brand : prod.getBrand()) {
			preselectedBrands += (brand.getName() + ",");
		}
		String preselectedCategories = "";
		for (Category category : prod.getCategory()) {
			preselectedCategories += (category.getCategoryName() + ",");
		}
		String preselectedColor = "";
		for (Color color : prod.getColor()) {
			preselectedColor += (color.getColorName() + ",");
		}
		String preselectedProdImg = "";
		for (Product_Img prodImg : prod.getProductImg()) {
			preselectedProdImg += (prodImg.getPicture1() + ",");
		}
		
		model.addAttribute("prod", prod);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedBrands", preselectedBrands);
		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("preselectedColor", preselectedColor);
		model.addAttribute("preselectedProdImg", preselectedProdImg);
		
		
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
		
		
		
//		List<Product> productList = productRepos.findAll();
//		model.addAttribute("productList", productList);
		
		if (productDetail.isPresent()) {
			model.addAttribute("ProductDetail", productDetail.get());
		}
		return "proDetail/Update";
	}
	
	
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("Product_Detail") Product_Detail product_Detail) {
		
		Product_Detail detail = product_DetailRepository.getById(id);

		product_DetailRepository.delete(detail);

	
		return "redirect:/admin";
	}
}
