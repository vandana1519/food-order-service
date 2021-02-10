package com.springboot.foodorderservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(name = "USER_ID")
	@NotNull(message = "Please provide userId")
	private Long userId;

	@Column(name = "TOTAL_PRICE", nullable = false)
	private Double totalPrice;

	@Column(name = "ORDER_DATE_TIME", nullable = false)
	private LocalDateTime orderDate;

	@Column(name = "FROM_ACCOUNT_NO", nullable = false, length = 100)
	@NotNull(message = "Please provide account Number")
	private Long fromAccountNumber;

	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	@NotEmpty(message = "Order Item must not be empty")
	private List<OrderItem> orderItems;

	public Orders() {
	}

	public Orders(Long userId, Double totalPrice, LocalDateTime orderDate, Long fromAccountNumber,
			List<OrderItem> orderItems) {
		super();
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.fromAccountNumber = fromAccountNumber;
		this.orderItems = orderItems;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@PrePersist
	void preInsert() {
		if (this.orderDate == null) {
			setOrderDate(LocalDateTime.now());
		}
	}

}
