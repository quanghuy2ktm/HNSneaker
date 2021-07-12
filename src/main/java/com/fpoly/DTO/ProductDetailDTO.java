package com.fpoly.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fpoly.model.Brand;
import com.fpoly.model.Category;
import com.fpoly.model.Color;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.model.Size;


public class ProductDetailDTO {

	private String title;
	private int stock;
	private double price;
	private List<String> prodImg;
	private List<String> size;
	private List<String> colors;
	private List<String> brands;
	private List<String> categories;
	
	public ProductDetailDTO() {
	
	}
	public Product_Detail build() {
		
		Date listingDate = new Date();
		
		Product_Detail productDetail = new Product_Detail();
		
		productDetail.setTitle(this.title);
		productDetail.setPrice(this.price);
		productDetail.setStock(this.stock);
		
		if(this.prodImg!=null && !this.prodImg.isEmpty()) {
			Set<Product_Img> prodImg = new HashSet<>();
			
			for(String image : this.prodImg) {
				prodImg.add(new Product_Img(image,productDetail));
			}
			
			productDetail.setProductImg(prodImg);
		}
		
		if(this.colors!=null && !this.colors.isEmpty()) {
			Set<Color> color = new HashSet<>();
			
			for(String colortemp : this.colors) {
				color.add(new Color(colortemp,productDetail));
			}
			
			productDetail.setColor(color);
		}
		
		if(this.size!=null && !this.size.isEmpty()) {
			Set<Size> size = new HashSet<>();
			
			for(String sizeTemp : this.size) {
				size.add(new Size(sizeTemp,productDetail));
			}
			
			productDetail.setSize(size);
		}
		
		if(this.brands!=null && !this.brands.isEmpty()) {
			Set<Brand> brand = new HashSet<>();
			
			for(String brandTemp : this.brands) {
				brand.add(new Brand(brandTemp,productDetail));
			}
			
			productDetail.setBrand(brand);
		}
		
		if(this.categories!=null && !this.categories.isEmpty()) {
			Set<Category> categories = new HashSet<>();
			
			for(String categoriesTemp : this.categories) {
				System.out.println(categoriesTemp);
				categories.add(new Category(categoriesTemp,productDetail,listingDate));
			}
			
			productDetail.setCategory(categories);
		}
		return productDetail;
	}
	
	public ProductDetailDTO color(List<String> color) {
		this.colors = color;
		return this;
	}
	
	public ProductDetailDTO withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ProductDetailDTO stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public ProductDetailDTO withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public ProductDetailDTO imageLink(List<String> picture) {
		this.prodImg = picture;
		return this;
	}
	
	public ProductDetailDTO sizesAvailable(List<String> sizes) {
		this.size = sizes;
		return this;
	}
	
	public ProductDetailDTO ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public ProductDetailDTO ofBrand(List<String> brands) {
		this.brands = brands;
		return this;
	}
	

}
