package org.shop.stroage.config;

/**
 * 
 * 节点配置
 * @author VIC
 *
 */
public class NodeConfig {

		/**节点服务名称: 表示该节点上的服务的名称,例如: 商城订单服务中的创建订单=shop.order.create*/
	    private String key;
	    /**节点服务值: 表示该节点上面的服务的URL,例如: 商城订单服务中的创建订单URL=http://192.168.1.188/shop.order/create*/
	    private String value;
	    /**节点服务代码: 例如: 商城订单服务中的创建订单代码=CreateOrder*/
	    private String code;
	    /**项目代码: 该节点或服务或系统属于哪个项目,例如: 商城订单服务的projectCode是ShopOrder*/
	    private String projectCode;
	    /**环境: 该节点部署的环境,例如: 开发环境=dev,测试环境=test,生成环境=online*/
	    private String environment;

	    public NodeConfig(){}
	    
	    public NodeConfig(String value) {
			this.value = value;
		}

		public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    public String getProjectCode() {
	        return projectCode;
	    }

	    public void setProjectCode(String projectCode) {
	        this.projectCode = projectCode;
	    }

	    public String getEnvironment() {
	        return environment;
	    }

	    public void setEnvironment(String environment) {
	        this.environment = environment;
	    }
}
