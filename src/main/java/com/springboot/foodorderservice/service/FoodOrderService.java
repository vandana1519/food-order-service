package com.springboot.foodorderservice.service;

import java.util.List;
import com.springboot.foodorderservice.entity.FoodItem;
import com.springboot.foodorderservice.entity.Orders;

public interface FoodOrderService {
	
	List<FoodItem> searchFoodItem(String foodItemDescription);
	
	List<Orders> getOrderHistory(Long userId);

	String placeOrder(Orders orders) throws Exception;

}
