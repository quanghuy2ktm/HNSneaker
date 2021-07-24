package com.fpoly.controller;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.config.UserSecurityService;
import com.fpoly.model.Cart_Item;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Role;
import com.fpoly.model.ShoppingCart;
import com.fpoly.model.User;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.repositories.User_RoleRepository;
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
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private RoleRepository userRoleRepos;
	
	@Autowired
	private User_RoleRepository uroleRepos;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {	
		
		User user = (User) authentication.getPrincipal();
		
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
		
		User user = new User();
		
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			 user = (User) authentication.getPrincipal();
			 
		}else {
			
			//Mua hàng không cần đăng ký
			System.out.println("user trống");
			//Tên người tạm
			int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 6;
		    Random random = new Random();
		    StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + (int) 
		          (random.nextFloat() * (rightLimit - leftLimit + 1));
		        buffer.append((char) randomLimitedInt);
		    }
		    String generatedString = buffer.toString();
		    
		    User uTemp = new User();
		    uTemp.setUsername("user_"+generatedString);
		    uTemp.setPassword(generatedString);
		    userRepos.save(uTemp);
		    
			// thêm quyền;
			if (uTemp != null) {
				Role role = userRoleRepos.findByRoleName("ROLE_USER");
				User_Role u = new User_Role();
				u.setRole(role);
				u.setUser(uTemp);
				uroleRepos.save(u);
			}
			System.out.println("user đã tạo: "+ uTemp);
			userSecurityService.authenticateUser(uTemp.getUsername());
			user = uTemp;
		}
		
		

		
		System.out.println("-----Check 3----");

		shoppingCartService.addArticleToShoppingCart(article, user, Integer.parseInt(qty), size);
		
		attributes.addFlashAttribute("addArticleSuccess", true);
		return "redirect:/article-detail?id="+article.getIDProductDetail();
	}
	
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Integer cartItemId,
									 @RequestParam("qty") Integer qty, Model model, RedirectAttributes attributes) {		
		
		Cart_Item cartItem = shoppingCartService.findCartItemById(cartItemId);
		
		
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}else {
			attributes.addFlashAttribute("notEnoughStock", true);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Integer id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	} 
}
