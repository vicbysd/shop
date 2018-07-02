package org.shop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 实体超类:定义一些实体通用属性,所有实体继承该超类
 * @MappedSuperclass: 该注解标注该类是一个超类,不是一个业务类,不会生成表
 * 
 * @author VIC
 *
 */
@MappedSuperclass
public class SuperEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 * @GenericGenerator 该注解定义注解生成策略为uuid,所有id为字符串类型 
	 */
	@Id
	@Column(name = "id")
	protected String id;
	
	/** 状态 */
	@Column(name = "state")
	protected String state;
	
	/** 创建时间 */
	@Column(name = "createTime")
	protected Date createTime;
	
	/** 更新时间 */
	@Column(name = "updateTime")
	protected Date updateTime;
	
	/** 操作人*/
	@Column(name = "creator")
	protected String creator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public void init(){
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.state = EntityState.ENABLED.toString();
		this.createTime = new Date();
		this.updateTime = new Date();
		this.creator  = "SYSTEM";
		
	}
}
