package com.fpoly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDAdress;

	@Column(name = "streetAddress")
	private String streetAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "ReciverPhoneNumber")
	private String ReciverPhoneNumber;

	@Column(name = "ReciverName")
	private String ReciverName;

	public Integer getIDAdress() {
		return IDAdress;
	}

	public void setIDAdress(Integer iDAdress) {
		IDAdress = iDAdress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getReciverPhoneNumber() {
		return ReciverPhoneNumber;
	}

	public void setReciverPhoneNumber(String reciverPhoneNumber) {
		ReciverPhoneNumber = reciverPhoneNumber;
	}

	public String getReciverName() {
		return ReciverName;
	}

	public void setReciverName(String reciverName) {
		ReciverName = reciverName;
	}



}
