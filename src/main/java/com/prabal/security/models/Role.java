package com.prabal.security.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	private AppRole appRole;

	public Role(AppRole appRole) {
		super();
		this.appRole = appRole;
	}

	public Role() {
		super();
	}

	public Role(Long roleId) {
		super();
		this.roleId=roleId;
	}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", appRole=" + appRole + "]";
	}
	

}
