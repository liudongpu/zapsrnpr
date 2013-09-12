package com.srnpr.zapweb.usermodel;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MObjMap;

public class MUserInfo {

	/**
	 * 用户名
	 */
	private String loginName = "";

	/**
	 * 真实姓名
	 */
	private String realName = "";

	/**
	 * 用户管理编号
	 */
	private String manageCode = "";

	/**
	 * 菜单信息
	 */
	private List<MMenuInfo> menuInfos = new ArrayList<MMenuInfo>();

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

}
