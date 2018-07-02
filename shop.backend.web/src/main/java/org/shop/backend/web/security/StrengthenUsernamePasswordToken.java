package org.shop.backend.web.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.shop.backend.common.dto.AdministratorDto;
import org.shop.backend.service.system.IAdministratorService;

/**
 * 基于用户名和密码的令牌(加强版)
 * @author VIC
 *
 */
public class StrengthenUsernamePasswordToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = 1L;
	/**管理员业务服务对象*/
	public IAdministratorService administratorService;
	/**验证码*/
	private String verifyCode; 

	public StrengthenUsernamePasswordToken(IAdministratorService administratorService, AdministratorDto administratorDto) {
		super(administratorDto.getAccount(), administratorDto.getPassword());
		this.administratorService = administratorService;
		this.verifyCode = administratorDto.getVerifyCode();
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
