package org.shop.util;

public class AdministratorDtoTmp {
	
	private String account;
	
	private String password;
	
	private String verifyCode;
	
	private RoleTmp roleTmp = new RoleTmp();

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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	
	
	public RoleTmp getRoleTmp() {
		return roleTmp;
	}

	public void setRoleTmp(RoleTmp roleTmp) {
		this.roleTmp = roleTmp;
	}

	@Override
	public String toString() {
		return "AdministratorDtoTmp [account=" + account + ", password=" + password + ", verifyCode=" + verifyCode
				+ ", roleTmp=" + roleTmp + "]";
	}

	

}
