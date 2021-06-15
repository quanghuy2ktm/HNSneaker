package com.fpoly.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	public Category() {
		
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
