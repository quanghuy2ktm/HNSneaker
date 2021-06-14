package com.fpoly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Color")
public class Color {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDColor;
	private String ColorName;
	
	@ManyToOne
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDetail;
	
	public Color() {
		
	}

	public Integer getIDColor() {
		return IDColor;
	}

	public void setIDColor(Integer iDColor) {
		IDColor = iDColor;
	}

	public String getColorName() {
		return ColorName;
	}

	public void setColorName(String colorName) {
		ColorName = colorName;
	}

	public Product_Detail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}
	
	
	
	
	
}
