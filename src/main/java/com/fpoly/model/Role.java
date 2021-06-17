package com.fpoly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer IDRole;
	
	@Column(name = "RoleName")
	private String RoleName;
	
	
	public Role() {
		
	}
	public Integer getIDRole() {
		return IDRole;
	}
	public void setIDRole(Integer iDRole) {
		IDRole = iDRole;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String RoleName) {
		this.RoleName = RoleName;
	}
	@Override
	public String toString() {
		return "Role [IDRole=" + IDRole + ", RoleName=" + RoleName + "]";
	}
	
	
}
