package com.fpoly.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.config.UserSecurityService;
import com.fpoly.model.Address;
import com.fpoly.model.Cart;
import com.fpoly.model.Role;
import com.fpoly.model.User;
import com.fpoly.model.User_Role;
import com.fpoly.repositories.AddressRepository;
import com.fpoly.repositories.CartRepository;
import com.fpoly.repositories.RoleRepository;
import com.fpoly.repositories.UserRepository;
import com.fpoly.repositories.User_RoleRepository;
import com.fpoly.service.OrderService;
import com.fpoly.service.UserService;

@Controller
public class AccountController {

	@Autowired
	RoleRepository roleRepos;

	@Autowired
	private User_RoleRepository userRoleRepo;

	@Autowired
	UserRepository repos;

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AddressRepository addressRepos;
	
	@RequestMapping("/login")
	public String log(Model model) {
		model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
		model.addAttribute("emailExists", model.asMap().get("emailExists"));
		return "myAccount";
	}

	@RequestMapping("/my-profile")
	public String myProfile(Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "user/myProfile";
	}

	@RequestMapping("/my-orders")
	public String myOrders(Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		List<Cart> orders = orderService.findByUser(user);
		model.addAttribute("orders", orders);
		return "user/myOrders";
	}

	@RequestMapping("/my-address")
	public String myAddress(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		System.out.println(user);
		model.addAttribute("user", user);
		return "user/myAddress";
	}

	@RequestMapping(value = "/update-user-address", method = RequestMethod.POST)
	public String updateUserAddress(@ModelAttribute("address") Address address, Model model, Principal principal)
			throws Exception {
		User currentUser = userService.findByUsername(principal.getName());
		if (currentUser == null) {
			throw new Exception("User not found");
		}
		
		System.out.println(address);
		addressRepos.save(address);
		
		currentUser.setAddress(address);

		userService.save(currentUser);
		
		return "redirect:/my-address";
	}

	@RequestMapping(value = "/new-user", method = RequestMethod.POST)
	public String newUserPost(@ModelAttribute("user") User user, BindingResult bindingResults,
			RedirectAttributes redirectAttributes, Model model) {

		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());

		boolean invalidFields = false;

		if (bindingResults.hasErrors()) {
			return "redirect:/login";
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("usernameExists", true);
			invalidFields = true;
		}
		if (userService.findByEmail(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("emailExists", true);
			invalidFields = true;
		}
		if (invalidFields) {
			return "redirect:/login";
		}
		System.out.println("-=-----------");
		System.out.println("user Detail: " + user.getUsername() + " - " + user.getPassword() + " - " + user.getEmail());
//		user = userService.createUser(user.getUsername(), user.getPassWord(),  user.getEmail(), Arrays.asList("ROLE_USER"));	

		User userTemp = new User();
		userTemp.setEmail(user.getEmail());
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userTemp.setPassword(user.getPassword());
		userTemp.setUsername(user.getUsername());
		repos.save(userTemp);

		// thêm quyền;
		if (userTemp != null) {
			Role role = roleRepos.findByRoleName("ROLE_USER");
			User_Role u = new User_Role();
			u.setRole(role);
			u.setUser(userTemp);
			userRoleRepo.save(u);
		}
		userSecurityService.authenticateUser(user.getUsername());

		return "myAccount";
//		return "redirect:/my-profile";  
	}

	@RequestMapping(value = "/update-user-info", method = RequestMethod.POST)
	public String updateUserInfo(@ModelAttribute("user") User user, @ModelAttribute("new-password") String newPass,
			Model model, Principal principal) throws Exception {
		User currentUser = userService.findByUsername(principal.getName());
		if (currentUser == null) {
			throw new Exception("User not found");
		}
		/* check username already exists */
		User existingUser = userService.findByUsername(user.getUsername());
		if (existingUser != null && !existingUser.getIDUser().equals(currentUser.getIDUser())) {
			model.addAttribute("usernameExists", true);
			return "user/myProfile";
		}
		/* check email already exists */
		existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null && !existingUser.getIDUser().equals(currentUser.getIDUser())) {
			model.addAttribute("emailExists", true);
			return "user/myProfile";
		}
		/* update password */
		/*
		 * if (newPassword != null && !newPassword.isEmpty() &&
		 * !newPassword.equals("")){
		 * 
		 * BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
		 * String dbPassword = currentUser.getPassword();
		 * if(passwordEncoder.matches(user.getPassword(), dbPassword)){
		 * currentUser.setPassword(passwordEncoder.encode(newPassword)); } else {
		 * model.addAttribute("incorrectPassword", true); return "myProfile"; } }
		 */
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
//		currentUser.setPassword(user.getPassword());
		currentUser.setPassword(newPass);
		userService.save(currentUser);

		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);

		userSecurityService.authenticateUser(currentUser.getUsername());
		return "user/myProfile";
	}

	@RequestMapping("/order-detail")
	public String orderDetail(@RequestParam("order") Integer id, Model model) {
		Cart order = orderService.findOrderWithDetails(id);

		model.addAttribute("order", order);
		return "Cart/orderDetails";
	}

}
