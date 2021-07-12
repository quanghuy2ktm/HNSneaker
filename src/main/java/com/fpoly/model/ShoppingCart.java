package com.fpoly.model;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
	
	private List<Cart_Item> cartItems;

	public ShoppingCart(List<Cart_Item> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		BigDecimal cartTotal = new BigDecimal(0);
		for (Cart_Item item : this.cartItems) {
			cartTotal = cartTotal.add(item.getSubtotal());
		}
		return cartTotal;
	}
	
	public boolean isEmpty() {
		return cartItems.isEmpty();
	}
	
	public void removeCartItem(Cart_Item cartItem) {
		cartItems.removeIf(item -> item.getIDCartItem() == cartItem.getIDCartItem());
	}
	
	public void clearItems() {
		cartItems.clear();
	}
	
	public Cart_Item findCartItemByArticleAndSize(Integer id, String size) {
		for (Cart_Item item : this.cartItems) {
			if (item.getProductDetail().getIDProductDetail().equals(id) && item.getSize().equals(size)) {
				return item;
			}
		}
		return null;
	}
	
	public int getItemCount() {
		return this.cartItems.size();
	}	
	
	public List<Cart_Item> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<Cart_Item> cartItems) {
		this.cartItems = cartItems;
	}

	
}
