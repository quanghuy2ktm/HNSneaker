package com.fpoly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "brand")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDBrand;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDetail;
	
	public Brand() {
		
	}

	
	public Brand(String brandTemp, Product_Detail productDetail2) {
		this.name = brandTemp;
		this.productDetail = productDetail2;
	}


	public Product_Detail getProductDetail() {
		return productDetail;
	}


	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}


	public Integer getIDBrand() {
		return IDBrand;
	}

	public void setIDBrand(Integer iDBrand) {
		IDBrand = iDBrand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
	
}
