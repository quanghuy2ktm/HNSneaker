package com.fpoly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDProduct;
	
	@Column(name = "ProductStatus")
	private boolean productStatus;
	
	@Column(name = "Title")
	private String title;
	
	public Product() {
		
	}
	
	public Integer getIDProduct() {
		return IDProduct;
	}
	public void setIDProduct(Integer iDProduct) {
		IDProduct = iDProduct;
	}
	public boolean isProductStatus() {
		return productStatus;
	}
	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
