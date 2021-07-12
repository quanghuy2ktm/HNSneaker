package com.fpoly.model;

import java.math.BigDecimal;
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
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDCart;
	
	@Column(name = "orderDate")
	private Date orderDate;

	@Column(name = "orderStatus")
	private String orderStatus;

	@Column(name = "orderTotal")
	private BigDecimal orderTotal;

	@Column(name = "shippingDate")
	private Date shippingDate;

	@Column(name = "Buyer_status")
	private String Buyer_status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Cart () {
		
	}

	public Integer getIDCart() {
		return IDCart;
	}

	public void setIDCart(Integer iDCart) {
		IDCart = iDCart;
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

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal bigDecimal) {
		this.orderTotal = bigDecimal;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getBuyer_status() {
		return Buyer_status;
	}

	public void setBuyer_status(String buyer_status) {
		Buyer_status = buyer_status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
}
