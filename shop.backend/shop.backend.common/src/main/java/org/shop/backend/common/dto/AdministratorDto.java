package org.shop.backend.common.dto;

import java.util.List;


public class AdministratorDto {
	
	private String account;
	
	private String password;
	
	private String verifyCode;
	
	private String state;
	
	private boolean rememberMe;

	public String getAccount() {
		return account;
	}
	
	private List<RoleDto> roles;
	
	private List<PermissionDto> permissions;

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}

}
