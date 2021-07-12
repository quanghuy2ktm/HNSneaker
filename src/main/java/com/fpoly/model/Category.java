package com.fpoly.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDCategory;
	
	@Column(name = "CategoryName")
	private String categoryName;

	@Column(name = "CategoryListingDate")
	private Date categoryListingDate;

	@Column(name = "CategoryStatus")
	private boolean categoryStatus;
	
	@ManyToOne
	@JoinColumn(name = "Product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDetail;
	
	
	public Product_Detail getProductDetail() {
		return productDetail;
	}


	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}


	public Category() {
		
	}


	public Category(String categoryTemp, Product_Detail productDetail2, Date date) {
		this.categoryName = categoryTemp;
		this.productDetail = productDetail2;
		this.categoryListingDate = date;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getIDCategory() {
		return IDCategory;
	}


	public void setIDCategory(Integer iDCategory) {
		IDCategory = iDCategory;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Date getCategoryListingDate() {
		return categoryListingDate;
	}


	public void setCategoryListingDate(Date categoryListingDate) {
		this.categoryListingDate = categoryListingDate;
	}


	public boolean isCategoryStatus() {
		return categoryStatus;
	}


	public void setCategoryStatus(boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	
	
}
