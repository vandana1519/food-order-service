package com.springboot.foodorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.springboot.foodorderservice.config.RibbonConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name="paymentclient", configuration = RibbonConfiguration.class)
public class FoodOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderServiceApplication.class, args);
	}

}
