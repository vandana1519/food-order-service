package com.springboot.foodorderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.springboot.foodorderservice.dto.PaymentDto;

@FeignClient(name = "http://BANKING-MANAGEMENT-SERVICE/bank")
public interface PaymentClient {

	@PostMapping("/transferFund")
	public ResponseEntity<String> transferFund(@RequestBody PaymentDto paymentDto);
}
