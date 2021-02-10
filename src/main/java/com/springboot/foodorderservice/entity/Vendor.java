package com.springboot.foodorderservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Vendor")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorId;
	private String vendorName;
	private String location;
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FoodItem> foodItem;

	public Vendor() {}
	
	public Vendor(Long vendorId, String vendorName, String location,List<FoodItem> foodItem) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.location = location;
		 if (foodItem != null ) {
	            this.foodItem = foodItem;
	            for (FoodItem item : foodItem)
	                item.setVendor(this);
	        }
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<FoodItem> getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(List<FoodItem> foodItem) {
		this.foodItem = foodItem;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

}
