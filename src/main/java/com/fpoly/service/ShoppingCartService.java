package com.fpoly.service;

import org.springframework.stereotype.Service;

import com.fpoly.model.Cart_Item;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;

@Service
public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	Cart_Item findCartItemById(Integer cartItemId);
	
	Cart_Item addArticleToShoppingCart(Product_Detail article, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(Cart_Item cartItem, Integer qty);

	void removeCartItem(Cart_Item cartItem);

}
