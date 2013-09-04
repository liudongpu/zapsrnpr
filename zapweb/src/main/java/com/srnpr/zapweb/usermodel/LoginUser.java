package com.srnpr.zapweb.usermodel;

import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmethod.WebMethod;

public class LoginUser {

	private final static WebMethod webMethod = new WebMethod();

	/**
	 * 定义是否登录
	 */
	private boolean bLoginFlag = false;

	/**
	 * 定义用户信息
	 */
	private MUserInfo userInfo = null;

	/**
	 * 获取登录标记
	 * 
	 * @return
	 */
	public boolean upLoginFlag() {

		Object oUserInfo = webMethod.upSession(WebConst.CONST_WEB_SESSION_USER);
		if (oUserInfo != null) {
			userInfo = ((MUserInfo) oUserInfo);
			bLoginFlag = true;
		}
		return bLoginFlag;
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public MUserInfo upUserInfo() {

		if (!bLoginFlag) {
			upLoginFlag();
		}
		return userInfo;
	}

}
