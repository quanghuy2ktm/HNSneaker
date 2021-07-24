package com.fpoly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.model.Cart;
import com.fpoly.model.Product;
import com.fpoly.model.Product_Detail;
import com.fpoly.model.Product_Img;
import com.fpoly.repositories.BrandRepository;
import com.fpoly.repositories.CartRepository;
import com.fpoly.repositories.CategoryRepository;
import com.fpoly.repositories.ColorRepository;
import com.fpoly.repositories.ProductRepository;
import com.fpoly.repositories.Product_DetailRepository;
import com.fpoly.repositories.Product_ImgRepository;
import com.fpoly.repositories.SizeRepository;
import com.fpoly.service.OrderService;
import com.fpoly.service.Product_DetailService;

@Controller
@RequestMapping("admin")
public class AdminControlle {

	@Autowired
	Product_DetailRepository product_DetailRepository;

	@Autowired
	Product_DetailService product_DetailService;

	@Autowired
	ProductRepository productRepos;
	
	@Autowired
	Product_ImgRepository prodimgRepo;
	
	@Autowired
	SizeRepository sizeRepo;
	
	@Autowired
	BrandRepository brandRepo;
	
	@Autowired
	ColorRepository colorRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	private CartRepository cartRepos;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "")
	public String getAll1(Model model) {
		List<Product_Detail> list = product_DetailRepository.findAll();
		model.addAttribute("list", list);
		
		List<Product> productList = productRepos.findAll();
		model.addAttribute("productList", productList);
		
		List<Product_Img> prodImg = prodimgRepo.findAll();
		model.addAttribute("prodImg", prodImg);
		
		List<String> sizeList = sizeRepo.findAllSizes();
		model.addAttribute("sizeList", sizeList);
		
		List<String> brandList = brandRepo.findAllBrand();
		model.addAttribute("brandList", brandList);
		
		List<String> colorList = colorRepo.findAllColor();
		model.addAttribute("colorList", colorList);
		
		List<String> categoryList = categoryRepo.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		
		return "admin/show";
	}
	
	@RequestMapping(value="orderlist")
	public String getOrderList(Model model) {
		List<Cart> cartList = cartRepos.findByDate();
		model.addAttribute("cartList", cartList);
		return "admin/OrderList";
	}
	
	@RequestMapping("/order-detail")
	public String orderDetail(@RequestParam("order") Integer id, Model model) {
		Cart order = orderService.findOrderWithDetails(id);
		model.addAttribute("order", order);
		return "admin/orderDetails";
	}
	@RequestMapping(value="order-detail-update/{id}",method = RequestMethod.POST)
	public String updateOD(@ModelAttribute("order")Cart order, @PathVariable Integer id, RedirectAttributes attr, Model model) {
		System.out.println("order: "+order.getOrderStatus() + order.getBuyer_status());
		Cart orderTemp = cartRepos.getById(id);
		System.out.println("orderTemp: "+orderTemp.getOrderStatus());
		
		if(orderTemp.getOrderStatus().equals("Đang vận chuyển") && order.getOrderStatus().equals("Hủy đơn")) {
			attr.addFlashAttribute("alert", true);
			attr.addFlashAttribute("message", "Đang trong quá trình vận chuyển, không thể hủy đơn hàng!");
			return "redirect:/admin/orderlist";
		}
		
		if(orderTemp.getOrderStatus().equals("Chờ xét duyệt") && order.getOrderStatus().equals("Đang vận chuyển")) {
			attr.addFlashAttribute("alert", true);
			attr.addFlashAttribute("message", "Đơn hàng đang chờ xét duyệt, không thể vận chuyển!");
			return "redirect:/admin/orderlist";
		}
		
		if(orderTemp.getOrderStatus().equals("Đang đóng gói") && order.getOrderStatus().equals("Hoàn thành")) {
			attr.addFlashAttribute("alert", true);
			attr.addFlashAttribute("message", "Đơn hàng đang đóng gói, không thể hoàn thành!");
			return "redirect:/admin/orderlist";
		}
		
		if(orderTemp.getOrderStatus().equals("Chờ xét duyệt") && order.getOrderStatus().equals("Hoàn thành")) {
			attr.addFlashAttribute("alert", true);
			attr.addFlashAttribute("message", "Đơn hàng đang chờ xét duyệt, không thể hoàn thành!");
			return "redirect:/admin/orderlist";
		}
		
		if(orderTemp.getOrderStatus().equals("Hủy đơn") && order.getOrderStatus().equals("Hoàn thành")) {
			attr.addFlashAttribute("alert", true);
			attr.addFlashAttribute("message", "Đơn hàng đã bị hủy");
			return "redirect:/admin/orderlist";
		}
		
		orderTemp.setOrderStatus(order.getOrderStatus());
		orderTemp.setBuyer_status(order.getBuyer_status());
		cartRepos.save(orderTemp);
		attr.addFlashAttribute("alertSuccess", true);
		return "redirect:/admin/orderlist";
	}
	
	
}
