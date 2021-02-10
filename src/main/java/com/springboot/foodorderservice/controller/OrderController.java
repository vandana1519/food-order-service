package com.springboot.foodorderservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.foodorderservice.dto.User;
import com.springboot.foodorderservice.entity.FoodItem;
import com.springboot.foodorderservice.entity.Orders;
import com.springboot.foodorderservice.exception.InvalidAccountException;
import com.springboot.foodorderservice.service.FoodOrderService;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {
	
	@Autowired
	FoodOrderService foodOrderService;

	/*
	 * static Map<String, List<FoodOrder>> foodOrders = new HashMap<>();
	 * 
	 * static { List<FoodOrder> orders = new ArrayList<>();
	 * 
	 * FoodOrder order = new FoodOrder(); order.setFoodItemId(101);
	 * order.setFoodItemDescription("Chicken Biryani"); order.setPrice((double)
	 * 350); order.setVendorId(1); order.setVendorName("Delhi Darbar");
	 * orders.add(order);
	 * 
	 * order = new FoodOrder(); order.setFoodItemId(102);
	 * order.setFoodItemDescription("Chicken 65"); order.setPrice((double) 300);
	 * order.setVendorId(1); order.setVendorName("Delhi Darbar"); orders.add(order);
	 * 
	 * order = new FoodOrder(); order.setFoodItemId(103);
	 * order.setFoodItemDescription("Chilly Chicken"); order.setPrice((double) 350);
	 * order.setVendorId(3); order.setVendorName("Hyderabadi Spice");
	 * orders.add(order);
	 * 
	 * foodOrders.put("chicken", orders);
	 * 
	 * orders = new ArrayList<>();
	 * 
	 * order = new FoodOrder(); order.setFoodItemId(201);
	 * order.setFoodItemDescription("Paneer Butter Masala"); order.setPrice((double)
	 * 300); order.setVendorId(4); order.setVendorName("Veggie India");
	 * orders.add(order);
	 * 
	 * order = new FoodOrder(); order.setFoodItemId(202);
	 * order.setFoodItemDescription("Paneer Masala"); order.setPrice((double) 350);
	 * order.setVendorId(1); order.setVendorName("Delhi Darbar"); orders.add(order);
	 * 
	 * order = new FoodOrder(); order.setFoodItemId(203);
	 * order.setFoodItemDescription("Paneer Chilly"); order.setPrice((double) 350);
	 * order.setVendorId(4); order.setVendorName("Veggie India"); orders.add(order);
	 * 
	 * foodOrders.put("paneer", orders); }
	 */
	static Map<String, User> userDetails = new HashMap<>();

	static {
		User user = new User();
		user.setUserId(100);
		user.setUserName("VD");

		userDetails.put("100", user);

		user = new User();
		user.setUserId(101);
		user.setUserName("Vandana");

		userDetails.put("101", user);

		user = new User();
		user.setUserId(102);
		user.setUserName("Sanjana");

		userDetails.put("102", user);

	}

	@GetMapping("/searchFood")
	public List<FoodItem> searchFoodItem(@RequestParam String foodItemDescription) {
		return foodOrderService.searchFoodItem(foodItemDescription);
	}

	@GetMapping("/orderHistory/{userId}")
	public List<Orders> getOrderHistory(@PathVariable Long userId) {
		return foodOrderService.getOrderHistory(userId);
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<String> placeOrder(@Valid @RequestBody Orders orders) throws Exception {
		String validationResponse = "";
		try {
			validationResponse = validateInputObject(orders);
			return new ResponseEntity<>(foodOrderService.placeOrder(orders), HttpStatus.OK);
		} catch (InvalidAccountException ex) {
			return new ResponseEntity<>("Invalid Account Number", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(validationResponse);
		}

	}

	public String validateInputObject(Orders orders) {

		if (orders.getUserId() == null || orders.getUserId() == 0) {
			return "The user ID is either missing or invalid! Please try again.";
		} else if (orders.getFromAccountNumber() == null || orders.getFromAccountNumber() == 0) {
			return "Account Number is either missing or invalid! Please try again.";
		} else if (orders.getOrderItems().isEmpty() || orders.getOrderItems() == null) {
			return "Please select a food item before placing the order!";
		} else
			return "Validation Successful";
	}
}