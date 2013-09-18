package com.srnpr.zapweb.webfactory;

import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.srnpr.zapcom.baseface.IBaseFactory;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmethod.WebMethod;
import com.srnpr.zapweb.webmodel.MWebResult;

public class UserFactory implements IBaseInstance, IBaseFactory<MUserInfo> {

	public static final UserFactory INSTANCE = new UserFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseFactory#create()
	 */
	public MUserInfo create() {

		MUserInfo mUserInfo = new MUserInfo();

		Object oUserInfo = WebSessionHelper.create().upSession(
				WebConst.CONST_WEB_SESSION_USER);

		if (oUserInfo != null) {
			mUserInfo = (MUserInfo) oUserInfo;
		}

		return mUserInfo;

	}

	public void inUserInfo(MUserInfo mUserInfo) {

		WebSessionHelper.create().inSession(WebConst.CONST_WEB_SESSION_USER,
				mUserInfo);

	}

	public MWebResult userLogin(String sLoginName, String sPassword) {

		MWebResult mResult = new MWebResult();

		MDataMap mUserInfo = DbUp.upTable("za_userinfo").one("user_name",
				sLoginName);

		if (mResult.upFlagTrue()) {
			// 判断是用户是否存在
			if (mUserInfo == null) {
				mResult.inErrorMessage(969905015);
			}
			// 判断用户是否被冻结
			else if (!StringUtils.equals(mUserInfo.get("flag_enable"), "1")) {
				mResult.inErrorMessage(969905014);
			}
		}

		if (mResult.upFlagTrue()) {

			// 定义最大失败次数
			int iMaxFaieldCount = 10;
			// 定义失败分钟数
			int iMimute = 10;

			Long lFaield = Long.parseLong(mUserInfo.get("failed_count"));

			// 判断最大失败次数
			if (lFaield >= iMaxFaieldCount) {

				// 开始判断最大失败次数
				try {

					Date dFailDate = DateUtils.addMinutes(FormatHelper
							.parseDate(mUserInfo.get("failed_time")), iMimute);

					if (dFailDate.after(new Date())) {
						mResult.inErrorMessage(969905016, iMimute);
					}
				} catch (Exception e) {
					e.printStackTrace();
					mResult.inErrorMessage(969905016, iMimute);
				}
			}

			if (mResult.upFlagTrue()) {

				// 判断如果密码不对则
				if (!SecrurityHelper.MD5(sPassword).equalsIgnoreCase(
						mUserInfo.get("user_password").trim())) {
					// 如果密码不对时 增加冻结次数
					mUserInfo.put("failed_count", String.valueOf(lFaield + 1));
					DbUp.upTable("za_userinfo").dataUpdate(mUserInfo,
							"failed_count", "uid");
 
				}

			}
		}

		return mResult;
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
