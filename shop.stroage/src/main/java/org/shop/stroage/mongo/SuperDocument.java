package org.shop.stroage.mongo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Mongo 文档超类,所有其他文档继承该类
 * @author VIC
 *
 */
public class SuperDocument implements Serializable {

	private static final long serialVersionUID = 1L;

	/**文档ID*/
	protected String id;
	
	/**操作人:默认为system*/
	protected String creator = "system";

	/**文档状态:默认enabled */
	protected DocumentStatus status;

	/**文档创建时间:默认当前系统时间 */
	protected Long createTime = System.currentTimeMillis();

	/**文档更新时间:默认当前系统时间*/
	protected Long updateTime = System.currentTimeMillis();

	public SuperDocument() {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.status = DocumentStatus.ENABLED;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
}
