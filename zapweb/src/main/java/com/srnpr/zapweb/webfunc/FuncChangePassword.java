package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebCheckHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfactory.WebLogFactory;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.UserSupport;

/**
 * 修改密码
 * 
 * @author srnpr
 * 
 */
public class FuncChangePassword extends RootFunc {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MDataMap mWindowMap = mDataMap.upSubMap("window_change_password_");

		MUserInfo mUserInfo = UserFactory.INSTANCE.create();

		// 判断输入参数
		if (mResult.upFlagTrue()) {

			mResult = WebCheckHelper.checkMap(mWindowMap,
					"old_password,new_password,new_password2",
					"469923180002,469923180005,469923180005",
					"969905040,969905041,969905042");

		}

		// 判断两个密码一致
		if (mResult.upFlagTrue()) {
			if (!StringUtils.equals(mWindowMap.get("new_password"),
					mWindowMap.get("new_password2"))) {
				mResult.inErrorMessage(969905031, bInfo(969905041),
						bInfo(969905042));
			}
		}

		// 判断原密码是否正确
		if (mResult.upFlagTrue()) {

			String sReplace = SecrurityHelper.MD5Customer(mWindowMap
					.get("old_password"));

			MDataMap mUserMap = DbUp.upTable("za_userinfo").one("user_code",
					mUserInfo.getUserCode());
			if (!mUserMap.get("user_password").equals(sReplace)) {
				mResult.inErrorMessage(967905002, bInfo(969905040));
			}

		}

		// 开始执行操作
		if (mResult.upFlagTrue()) {
			WebLogFactory.INSTANCE.addLog(
					"467723120002",
					"change_password",
					bInfo(969912003, mUserInfo.getUserCode(),
							mUserInfo.getUserCode()));

			MDataMap mUpdateMap = new MDataMap();
			mUpdateMap
					.put("user_password", SecrurityHelper
							.MD5Customer(mWindowMap.get("new_password")));

			mUpdateMap.put("user_code", mUserInfo.getUserCode());

			DbUp.upTable("za_userinfo").dataUpdate(mUpdateMap, "user_password",
					"user_code");

		}

		return mResult;

	}
}
