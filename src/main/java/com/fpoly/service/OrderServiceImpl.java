package com.fpoly.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.model.Cart;
import com.fpoly.model.Cart_Item;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;
import com.fpoly.repositories.CartRepository;
import com.fpoly.repositories.Cart_ItemRepository;
import com.fpoly.repositories.Product_DetailRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CartRepository orderRepository;
	
	@Autowired
	Cart_ItemRepository cartItemRepository;
	
	@Autowired
	Product_DetailRepository articleRepository;
			
	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Cart createOrder(ShoppingCart shoppingCart, User user) {
		Cart order = new Cart();
		order.setUser(user);
		order.setOrderTotal(shoppingCart.getGrandTotal());			
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);				
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("Chờ xét duyệt");
		
		order = orderRepository.save(order);
		
		List<Cart_Item> cartItems = shoppingCart.getCartItems();
		for (Cart_Item item : cartItems) {
			Product_Detail article = item.getProductDetail();
			article.decreaseStock(item.getQty());
			articleRepository.save(article);
			item.setCart(order);
			cartItemRepository.save(item);
		}		
		return order;	
	}
	
	@Override
	public Cart findOrderWithDetails(Integer id) {
		return orderRepository.getOne(id);
	}	

	public List<Cart> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

}
