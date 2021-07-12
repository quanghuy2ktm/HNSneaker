package com.fpoly.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@OneToMany(mappedBy = "product")
	private Set<Category> category;
	
	@OneToOne
	@JoinColumn(name = "Product_id")
	private Product_Detail productDetail;

	
	

	public Product_Detail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

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
