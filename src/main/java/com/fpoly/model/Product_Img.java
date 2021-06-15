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
@Table(name = "product_img")
public class Product_Img {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDProductImg;

	@Column(name = "Picture1")
	private String picture1;

	@Column(name = "Picture2")
	private String picture2;

	@Column(name = "Picture3")
	private String picture3;

	@Column(name = "Picture4")
	private String picture4;

	@ManyToOne
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDeltai;

	public Product_Img() {

	}

	public Integer getIDProductImg() {
		return IDProductImg;
	}

	public void setIDProductImg(Integer iDProductImg) {
		IDProductImg = iDProductImg;
	}

	public String getPicture1() {
		return picture1;
	}

	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getPicture2() {
		return picture2;
	}

	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	public String getPicture3() {
		return picture3;
	}

	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}

	public String getPicture4() {
		return picture4;
	}

	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}

	public Product_Detail getProductDeltai() {
		return productDeltai;
	}

	public void setProductDeltai(Product_Detail productDeltai) {
		this.productDeltai = productDeltai;
	}

}
