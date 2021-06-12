package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product_Detail")

public class Product_Detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDProductDetail;
	private boolean productStatus;
	private String title;
	private String title2;
	private String shortTitle;
	private String sku;
	private String description;
	private int stock; 
	private Integer price;
	private Date listingDate;
	private String Materials;
	@OneToOne
	@JoinColumn(name = "Product_id")
	private Product product;
	
	public Product_Detail() {
		
	}
	
	public Integer getIDProductDetail() {
		return IDProductDetail;
	}
	public void setIDProductDetail(Integer iDProductDetail) {
		IDProductDetail = iDProductDetail;
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
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getListingDate() {
		return listingDate;
	}
	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	public String getMaterials() {
		return Materials;
	}
	public void setMaterials(String materials) {
		Materials = materials;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
	
}
