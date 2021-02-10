package com.springboot.foodorderservice.dto;

import nonapi.io.github.classgraph.json.Id;

public class User {

	@Id
	private int userId;
	private String userName;
	private Long accountNumber;
	private int contactNumber;
	private String address;
	
	public User() {}
	
	public User(int userId, String userName, Long accountNumber, int contactNumber, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
