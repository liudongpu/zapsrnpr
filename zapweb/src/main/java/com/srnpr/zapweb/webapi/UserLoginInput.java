package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.topapi.RootInput;

public class UserLoginInput extends RootInput {

	/**
	 * 登录名
	 */
	private String loginName = "";

	/**
	 * 登录密码
	 */
	private String loginPass = "";

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

}
