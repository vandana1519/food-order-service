package com.springboot.foodorderservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.foodorderservice.interceptor.OrderInterceptor;

@Component
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	OrderInterceptor orderInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(orderInterceptor).addPathPatterns("/orders/**");
	}

}
