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
@Table(name = "bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDBill;
	
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "orderStatus")
	private String orderStatus;
	
	@Column(name = "orderTotal")
	private Integer orderTotal;
	
	@Column(name = "shippingDate")
	private Date shippingDate;
	
	@Column(name = "Buyer_status")
	private String buyer_Status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Bill() {
		
	}
	
	public Integer getIDBill() {
		return IDBill;
	}

	public void setIDBill(Integer iDBill) {
		IDBill = iDBill;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getBuyer_Status() {
		return buyer_Status;
	}

	public void setBuyer_Status(String buyer_Status) {
		this.buyer_Status = buyer_Status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
