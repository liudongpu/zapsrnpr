package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 超级管理员登陆
 * 
 * @author srnpr
 * 
 */
public class FuncManageLogin extends RootFunc {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MDataMap mSubMap = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		// 开始验证用户名密码是否空
		if (mResult.upFlagTrue()) {

			if (mSubMap.containsKey("login_name")
					&& mSubMap.containsKey("login_pass")
					&& StringUtils.isNotEmpty(mSubMap.get("login_name"))
					&& StringUtils.isNotEmpty(mSubMap.get("login_pass"))) {

			} else {
				mResult.inErrorMessage(969905013);
			}

		}

		// 获取用户信息
		if (mResult.upFlagTrue()) {

			String sLoginName = mSubMap.get("login_name");

			String sPassword = mSubMap.get("login_pass");

			MWebResult mLoginResult = UserFactory.INSTANCE.userLogin(
					sLoginName, sPassword);

			if (mLoginResult.upFlagTrue()) {

				mResult.setResultType("116018010");

				JsonHelper<MUserInfo> userJsonHelper = new JsonHelper<MUserInfo>();

				String sUserInfoString = userJsonHelper
						.ObjToString(UserFactory.INSTANCE.create());

				mResult.setResultObject(FormatHelper.formatString(
						bConfig("zapweb.login_success_js"), sUserInfoString));

			} else {
				mResult = mLoginResult;
			}

			/*
			 * MDataMap mUserInfo = DbUp.upTable("za_userinfo").one("user_name",
			 * sLoginName,"flag_enable", "1");
			 * 
			 * if (mUserInfo != null &&
			 * mUserInfo.get("flag_enable").equals("1")) {
			 * 
			 * } else { mResult.inErrorMessage(969905014); }
			 */

		}

		return mResult;

	}
}
