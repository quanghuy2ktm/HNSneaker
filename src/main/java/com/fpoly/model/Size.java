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
@Table(name = "size")
public class Size {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDSize;
	
	@Column(name = "value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDetail;
	
	public Size() {
		
	}

	public Integer getIDSize() {
		return IDSize;
	}

	public void setIDSize(Integer iDSize) {
		IDSize = iDSize;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product_Detail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public String toString() {
		return "Size [IDSize=" + IDSize + ", value=" + value + ", productDetail=" + productDetail + "]";
	}
	
	
	
	
}
