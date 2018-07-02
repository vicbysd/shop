package org.shop.backend.common.dto;

import java.util.List;

public class RoleDto {
	
	private String code;
	private String name;
	private List<AdministratorDto> administrators;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AdministratorDto> getAdministrators() {
		return administrators;
	}
	public void setAdministrators(List<AdministratorDto> administrators) {
		this.administrators = administrators;
	}
	

}
