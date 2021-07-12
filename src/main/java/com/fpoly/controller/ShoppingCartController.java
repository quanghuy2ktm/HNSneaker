package com.fpoly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.model.Cart_Item;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.service.ArticleService;
import com.fpoly.service.ShoppingCartService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	
	@Autowired
	Product_DetailRepository repos;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {		
		User user = (User) authentication.getPrincipal();
		if(user == null) {
			user = new User();
		}
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);	
		
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);		
		return "shoppingCart";
	}

	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("article") Product_Detail article, @RequestParam("qty") String qty,
						  @RequestParam("size") String size, RedirectAttributes attributes, Model model, Authentication authentication) {
		
		System.out.println("-----Check----");
		
		article = articleService.findArticleById(article.getIDProductDetail());			
		
		if (!article.hasStock(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true);
			return "redirect:/article-detail?id="+article.getIDProductDetail();
		}		
		System.out.println("-----Check 2----");
		
		User user = (User) authentication.getPrincipal();
		System.out.println(user);
//		if(user == null) {
//			user = new User();
//		}
		System.out.println("-----Check 3----");

		shoppingCartService.addArticleToShoppingCart(article, user, Integer.parseInt(qty), size);
		
		attributes.addFlashAttribute("addArticleSuccess", true);
		return "redirect:/article-detail?id="+article.getIDProductDetail();
	}
	
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Integer cartItemId,
									 @RequestParam("qty") Integer qty, Model model) {		
		Cart_Item cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Integer id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	} 
}
