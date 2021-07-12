package com.fpoly.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class Cart_Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDCartItem;

	@Column(name = "CartItemStatus")
	private boolean cartItemStatus;

	@Column(name = "color")
	private String color;

	@Column(name = "CartCode")
	private String cartCode;

	@Column(name = "qty")
	private int qty;

	@Column(name = "size")
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

	
	public boolean canUpdateQty(Integer qty) {
		return qty == null || qty <= 0 || this.getProductDetail().hasStock(qty);
	}
	
	public BigDecimal getSubtotal() {
		return new BigDecimal(productDetail.getPrice()).multiply(new BigDecimal(qty));
	}

	public void addQuantity(int qty) {
		if (qty > 0) {
			this.qty = this.qty + qty;
		}
	}
	
	public boolean hasSameSizeThan(String size2) {
		return this.size.equals(size2);
	}
	
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
