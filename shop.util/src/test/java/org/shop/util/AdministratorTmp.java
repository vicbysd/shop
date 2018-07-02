package org.shop.util;

public class AdministratorTmp {
	
	private String account;
	
	private String password;
	
	private String verifyCode;
	
	private boolean rememberMe;
	
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

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public RoleTmp getRoleTmp() {
		return roleTmp;
	}

	public void setRoleTmp(RoleTmp roleTmp) {
		this.roleTmp = roleTmp;
	}

	@Override
	public String toString() {
		return "AdministratorTmp [account=" + account + ", password=" + password + ", verifyCode=" + verifyCode
				+ ", rememberMe=" + rememberMe + ", roleTmp=" + roleTmp + "]";
	}
	
}
