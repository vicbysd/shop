package org.shop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 管理员
 * @author VIC
 *
 */
@Entity
@Table(name="tb_administrator")
public class Administrator extends SuperEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name="account")
	private String account;
	
	@Column(name="password")
	private String password;
	
	@Column(name="head",length=2048)
	private String head;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="qq")
	private String qq;
	
	@Column(name="type")
	private String type;
	
	@Column(name="remarks")
	private String remarks;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Role> roles;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Permission> permissions;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
