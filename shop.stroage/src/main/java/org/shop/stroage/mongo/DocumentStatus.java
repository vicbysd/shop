package org.shop.stroage.mongo;

/**
 * 文档状态
 * @author VIC
 *
 */
public enum DocumentStatus {

	ENABLED("ENABLED"), // 启用
	DISABLED("DISABLED"), // 停用
	LOCKED("LOCKED"), // 锁定
	DELETE("DELETE");// 删除
	private String value;

	private DocumentStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
