package com.springboot.foodorderservice.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.foodorderservice.dto.PaymentDto;
import com.springboot.foodorderservice.entity.FoodItem;
import com.springboot.foodorderservice.entity.OrderItem;
import com.springboot.foodorderservice.entity.Orders;
import com.springboot.foodorderservice.entity.Vendor;
import com.springboot.foodorderservice.feignclient.PaymentClient;
import com.springboot.foodorderservice.repository.FoodOrderRepository;
import com.springboot.foodorderservice.repository.OrderHistoryRepository;
import com.springboot.foodorderservice.repository.OrderItemsRepository;
import com.springboot.foodorderservice.repository.VendorRepository;
import com.springboot.foodorderservice.service.FoodOrderService;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {
	
	@Value("${bank.toAccountNumber}")
	private Long toAccountNumber;
	
	@Autowired
	PaymentClient paymentClient;
	
	@Autowired
	FoodOrderRepository foodOrderRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	OrderItemsRepository orderItemsRepository;
	
	@Autowired
	OrderHistoryRepository orderHistoryRepository;

	@Override
	public List<FoodItem> searchFoodItem(String foodItemDescription) {
		List<FoodItem> foodItems =  Optional.ofNullable(foodOrderRepository.findByFoodItemDescriptionContains(foodItemDescription)).orElseGet(Collections::emptyList).stream().collect(Collectors.toList());
			
			for (FoodItem foodItem : foodItems) {
	            Optional<Vendor> vendorProfile = vendorRepository.findById(foodItem.getVendorId());
	            if (vendorProfile.isPresent()) {
	            	foodItem.setVendorName(vendorProfile.get().getVendorName());
	            	foodItem.setVendor(null);
	            }	
	        }
	        return foodItems;
	}	

	@Override
	public List<Orders> getOrderHistory(Long userId) {
		
		List<Orders> ordersList = orderHistoryRepository.findByUserIdOrderByOrderIdDesc(userId).stream().limit(5).collect(Collectors.toList());

		for (Orders order : ordersList) {
            for (OrderItem orderItem : order.getOrderItems()) {
                Vendor vendorProfile = vendorRepository.getOne(orderItem.getVendorId());
                FoodItem foodItem = foodOrderRepository.findByFoodItemId(orderItem.getFoodItemId());
                orderItem.setVendorName(vendorProfile.getVendorName());
                orderItem.setFoodItemName(foodItem.getFoodItemDescription());
            }
        }
        return ordersList;
		
	}

	@Override
	public String placeOrder(Orders orders) throws Exception {
		try {
			double totalPrice = 0.0;
			orders.setTotalPrice(0.0);
			orders = orderHistoryRepository.save(orders);
			for (OrderItem orderItem : orders.getOrderItems()) {
				orderItem.setOrderId(orders.getOrderId());
				Optional<FoodItem> foodItem = foodOrderRepository.findById(orderItem.getFoodItemId());
				if (foodItem.isPresent()) {
					totalPrice = totalPrice + foodItem.get().getPrice() * orderItem.getQuantity();
				}
				orderItemsRepository.save(orderItem);
			}
			orders.setTotalPrice(totalPrice);
			
			//Calling Payment Gateway using Feign Client
			ResponseEntity responseEntity = paymentClient
					.transferFund(new PaymentDto(totalPrice, orders.getFromAccountNumber(), toAccountNumber));
			if (responseEntity.getStatusCode() == HttpStatus.OK)
				orders = orderHistoryRepository.save(orders);
			else if (responseEntity.getStatusCode() == HttpStatus.EXPECTATION_FAILED)
				return "Insufficient balance in your account!!!";
			else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST)
				return "Invalid Account Id!!!";
			return "Order placed successfully!!! Your Order Id is: " + orders.getOrderId();

		} catch (Exception e) {
			throw new Exception(
					"Caught exception while placing the order!! If your money is deducted, please contact Support Team..");
		}

	}

}
