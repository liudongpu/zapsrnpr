package com.srnpr.zapweb.usermodel;

import java.util.ArrayList;
import java.util.List;

public class MUserInfo {

	/**
	 * 用户名
	 */
	private String userName = "";

	/**
	 * 真实姓名
	 */
	private String realName = "";

	/**
	 * 菜单信息
	 */
	private List<MMenuInfo> menuInfos = new ArrayList<MMenuInfo>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<MMenuInfo> getMenuInfos() {
		return menuInfos;
	}

	public void setMenuInfos(List<MMenuInfo> menuInfos) {
		this.menuInfos = menuInfos;
	}

}
