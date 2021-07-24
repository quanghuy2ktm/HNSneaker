package com.fpoly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fpoly.model.Cart_Item;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;
import com.fpoly.repositories.Cart_ItemRepository;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private Cart_ItemRepository cartItemRepository;
	
	@Override
	public ShoppingCart getShoppingCart(User user) {
		return new ShoppingCart(cartItemRepository.findAllByUserAndCartIsNull(user));
	}
	
	@Override
	@Cacheable("itemcount")
	public int getItemsNumber(User user) {
		return cartItemRepository.countDistinctByUserAndCartIsNull(user);
	}

	@Override
	public Cart_Item findCartItemById(Integer cartItemId) {
		Optional<Cart_Item> opt = cartItemRepository.findById(cartItemId);
		return opt.get();
	}

	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public Cart_Item addArticleToShoppingCart(Product_Detail article, User user, int qty, String size) {
		
		ShoppingCart shoppingCart = this.getShoppingCart(user);
		System.out.println("total: "+ shoppingCart.getGrandTotal());
		System.out.println(article);
		Cart_Item cartItem = shoppingCart.findCartItemByArticleAndSize(article.getIDProductDetail(), size);
		
		if (cartItem != null && cartItem.hasSameSizeThan(size)) {
			cartItem.addQuantity(qty);
			cartItem.setSize(size);
			cartItem = cartItemRepository.save(cartItem);
		} else {
			cartItem = new Cart_Item();
			cartItem.setUser(user);
			cartItem.setProductDetail(article);
			cartItem.setQty(qty);
			cartItem.setSize(size);
			cartItem = cartItemRepository.save(cartItem);
		}		
		return cartItem;	
	}

	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public void removeCartItem(Cart_Item cartItem) {
		cartItemRepository.deleteById(cartItem.getIDCartItem());
	}
	
	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public void updateCartItem(Cart_Item cartItem, Integer qty) {
		if (qty == null || qty <= 0) {
			this.removeCartItem(cartItem);
		} else if (cartItem.getProductDetail().hasStock(qty)) {
			cartItem.setQty(qty);
			cartItemRepository.save(cartItem);
		}
	}

	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public void clearShoppingCart(User user) {
		cartItemRepository.deleteAllByUserAndCartIsNull(user);	
	}
	
}
