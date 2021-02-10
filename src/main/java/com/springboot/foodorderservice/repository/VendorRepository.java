package com.springboot.foodorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.foodorderservice.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
	String findVendorNameByVendorId(Long vendorId);

}
