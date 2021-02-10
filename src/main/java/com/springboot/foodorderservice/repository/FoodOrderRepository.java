package com.springboot.foodorderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.foodorderservice.entity.FoodItem;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodItem, Integer> {
	
	String findFoodItemDescriptionByFoodItemId(int foodItemId);
	
	FoodItem  findByFoodItemId(int footItemId);
	
	List<FoodItem> findByFoodItemDescriptionContains(@Param("foodItemDescription") String foodItemDescription);

}
