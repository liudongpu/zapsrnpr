package com.srnpr.zapweb.usermodel;

public class MUserInfo {

	/**
	 * 用户编号
	 */
	private String userCode = "";

	/**
	 * 用户登陆名
	 */
	private String loginName = "";

	/**
	 * 真实姓名
	 */
	private String realName = "";

	/**
	 * 登陆状态 1为登陆 0为未登陆
	 */
	private int flagLogin = 0;

	/**
	 * 用户管理店铺编号
	 */
	private String manageCode = "";

	/**
	 * 用户菜单编号串 竖线分隔
	 */
	private String userMenu = "";

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getManageCode() {
		return manageCode;
	}

	public void setManageCode(String manageCode) {
		this.manageCode = manageCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getFlagLogin() {
		return flagLogin;
	}

	public void setFlagLogin(int flagLogin) {
		this.flagLogin = flagLogin;
	}

	public String getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(String userMenu) {
		this.userMenu = userMenu;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
