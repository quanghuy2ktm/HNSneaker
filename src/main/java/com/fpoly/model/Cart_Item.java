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
@Table(name = "Cart_Item")
public class Cart_Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDCartItem;
	private boolean cartItemStatus;
	private String color;
	private String cartCode;
	private int qty;
	private String size;
	@ManyToOne
	@JoinColumn(name = "Product_Detail_id")
	private Product_Detail productDetail;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Cart_Item() {
		
	}

	public Integer getIDCartItem() {
		return IDCartItem;
	}

	public void setIDCartItem(Integer iDCartItem) {
		IDCartItem = iDCartItem;
	}

	public boolean isCartItemStatus() {
		return cartItemStatus;
	}

	public void setCartItemStatus(boolean cartItemStatus) {
		this.cartItemStatus = cartItemStatus;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCartCode() {
		return cartCode;
	}

	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Product_Detail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Product_Detail productDetail) {
		this.productDetail = productDetail;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
