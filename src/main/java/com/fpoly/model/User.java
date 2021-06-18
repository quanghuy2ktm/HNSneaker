package com.fpoly.model;

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
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDUser;
	
	@Column(name = "status")
	private boolean status;

	@Column(name = "email")
	private String email;
	
	
	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "passWord")
	private String passWord;

	@Column(name = "userName")
	private String userName;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "SecondPhoneNumber")
	private String secondPhoneNumber;
	
	@OneToOne
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "User [IDUser=" + IDUser + ", status=" + status + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", passWord=" + passWord + ", userName=" + userName + ", phoneNumber="
				+ phoneNumber + ", secondPhoneNumber=" + secondPhoneNumber + ", address=" + address + "]";
	}
	
	
	
}
