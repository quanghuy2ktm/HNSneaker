package com.fpoly.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_detail")

public class Product_Detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDProductDetail;

	@Column(name = "ProductStatus")
	private boolean productStatus;

	@Column(name = "title")
	private String title;

	@Column(name = "title2")
	private String title2;

	@Column(name = "ShortTitle")
	private String shortTitle;

	@Column(name = "Sku")
	private String sku;

	@Column(name = "description")
	private String description;

	@Column(name = "stock")
	private int stock;

	@Column(name = "price")
	private double price;

	@Column(name = "ListingDate")
	private Date listingDate;

	@Column(name = "Materials")
	private String Materials;

	@OneToOne
	@JoinColumn(name = "Product_id")
	private Product product;

	@OneToMany(mappedBy = "productDeltai",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Product_Img> ProductImg;
	
	@OneToMany(mappedBy = "productDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Size> size;
	
	@OneToMany(mappedBy = "productDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Color> color;
	
	@OneToMany(mappedBy = "productDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Brand> brand;
	
	@OneToMany(mappedBy = "productDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> category;
	
	
	
	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}
	
	public void decreaseStock(int amount) {
		this.stock -= amount;
	}
	
	
	
	
	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public Set<Product_Img> getProductImg() {
		return ProductImg;
	}

	public void setProductImg(Set<Product_Img> productImg) {
		ProductImg = productImg;
	}

	public Set<Size> getSize() {
		return size;
	}

	public void setSize(Set<Size> size) {
		this.size = size;
	}

	public Set<Color> getColor() {
		return color;
	}

	public void setColor(Set<Color> color) {
		this.color = color;
	}

	public Set<Brand> getBrand() {
		return brand;
	}

	public void setBrand(Set<Brand> brand) {
		this.brand = brand;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	@Override
	public String toString() {
		return "Product_Detail [IDProductDetail=" + IDProductDetail + ", productStatus=" + productStatus + ", title="
				+ title + ", title2=" + title2 + ", shortTitle=" + shortTitle + ", sku=" + sku + ", description="
				+ description + ", stock=" + stock + ", price=" + price + ", listingDate=" + listingDate
				+ ", Materials=" + Materials + ", product=" + product + "]";
	}

	
	
}
