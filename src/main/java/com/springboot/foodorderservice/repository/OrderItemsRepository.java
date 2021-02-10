package com.springboot.foodorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.foodorderservice.entity.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long>{

}
