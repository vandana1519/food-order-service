package com.springboot.foodorderservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FoodItem1")
public class FoodItem {
	
	@Id
	@Column(name="FOOD_ITEM_ID",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodItemId;
	
    @Column(name="VENDOR_ID",nullable = false)
    private Long vendorId;
	
	private String foodItemDescription;
	
	@Column(name = "PRICE",nullable = false)
	private Double price;
	
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "vendor_id", referencedColumnName = "vendorId",nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private Vendor vendor;
	
	public FoodItem() {}

	public FoodItem(int foodItemId, String foodItemDescription, Double price,Vendor vendor) {
		super();
		this.foodItemId = foodItemId;
		this.foodItemDescription = foodItemDescription;
		this.price = price;
		this.vendor= vendor;
	}
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public int getFoodItemId() {
		return foodItemId;
	}

	public void setFoodItemId(int foodItemId) {
		this.foodItemId = foodItemId;
	}

	public String getFoodItemDescription() {
		return foodItemDescription;
	}

	public void setFoodItemDescription(String foodItemDescription) {
		this.foodItemDescription = foodItemDescription;
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

	@Transient
	   private String vendorName;
}
