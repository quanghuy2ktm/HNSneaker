package com.fpoly.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
@NamedEntityGraph(
		name= "UserComplete",
		attributeNodes= { @NamedAttributeNode(value="userRoles", subgraph="role-subgraph") },
		subgraphs= { 
			@NamedSubgraph(name = "role-subgraph", attributeNodes = {  @NamedAttributeNode("role") }
		)}
	)
@Entity
@Table(name = "user")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@NotNull
	@Column(name = "passWord")
	private String password;

	@NotNull
	@Column(name = "userName")
	private String username;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "SecondPhoneNumber")
	private String secondPhoneNumber;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User_Role> userRoles = new HashSet<>();
	
	public User() {
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public Set<User_Role> getUserRoles() {
		return userRoles;
	}
	
	public void setUserRoles(Set<User_Role> userRoles) {
		this.userRoles = userRoles;
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

	public void setPassWord(String passWord) {
		this.password = passWord;
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
				+ ", lastName=" + lastName + ", passWord=" + password + ", userName=" + username + ", phoneNumber="
				+ phoneNumber + ", secondPhoneNumber=" + secondPhoneNumber + ", address=" + address + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(userRole -> authorites.add(new Authority(userRole.getRole().getRoleName())));
		return authorites;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
