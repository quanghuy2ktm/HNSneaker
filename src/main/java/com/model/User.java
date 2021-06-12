package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDUser;
	private boolean status;
	private String firstName;
	private String lastName;
	private String passWord;
	private String userName;
	private String phoneNumber;
	private String secondPhoneNumber;
	@OneToOne(cascade= CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="address_id")
	private Address address;
	
	public User() {
		
	}
	
	public Integer getIDUser() {
		return IDUser;
	}
	public void setIDUser(Integer iDUser) {
		IDUser = iDUser;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}
	public void setSecondPhoneNumber(String secondPhoneNumber) {
		this.secondPhoneNumber = secondPhoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
