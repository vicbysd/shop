package org.shop.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 管理员角色
 * @author VIC
 *
 */
@Entity
@Table(name="tb_role")
public class Role extends SuperEntity {

	private static final long serialVersionUID = 1L;
	/**角色代码*/
	@Column(name="code")
	private String code;
	/**角色名称*/
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private List<Administrator> administrators;
	
	
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
	public List<Administrator> getAdministrators() {
		return administrators;
	}
	public void setAdministrators(List<Administrator> administrators) {
		this.administrators = administrators;
	}
	
}
