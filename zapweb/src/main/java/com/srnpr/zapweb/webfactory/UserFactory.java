package com.srnpr.zapweb.webfactory;

import com.srnpr.zapcom.baseface.IBaseFactory;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmethod.WebMethod;

public class UserFactory implements IBaseInstance, IBaseFactory<MUserInfo> {

	public static final UserFactory INSTANCE = new UserFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseFactory#create()
	 */
	public MUserInfo create() {

		MUserInfo mUserInfo = new MUserInfo();

		Object oUserInfo = WebMethod.INSTANCE
				.upSession(WebConst.CONST_WEB_SESSION_USER);

		if (oUserInfo != null) {
			mUserInfo = (MUserInfo) oUserInfo;
		} else {

			mUserInfo = upTestUser();
			WebMethod.INSTANCE.inSession(WebConst.CONST_WEB_SESSION_USER,
					mUserInfo);
			// mUserInfo.setUserName(userName)
		}

		return mUserInfo;

	}

	public MUserInfo upTestUser() {
		// 测试专用
		MUserInfo mUserInfo = new MUserInfo();
		mUserInfo.setManageCode("MJ0001");
		mUserInfo.setLoginName("s");
		mUserInfo.setRealName("小测测");

		return mUserInfo;
	}

}
