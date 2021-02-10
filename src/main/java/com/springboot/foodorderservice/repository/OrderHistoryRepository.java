package com.springboot.foodorderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.foodorderservice.entity.Orders;

public interface OrderHistoryRepository extends JpaRepository<Orders, Long>{

	List<Orders> findByUserIdOrderByOrderIdDesc(Long userId);

}
