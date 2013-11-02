package com.srnpr.zapweb.webfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCreate;
import com.srnpr.zapcom.baseface.IBaseFactory;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmethod.WebMethod;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 用户
 * 
 * @author srnpr
 * 
 */
public class UserFactory extends BaseClass implements IBaseInstance,
		IBaseCreate {

	public static final UserFactory INSTANCE = new UserFactory();

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public MUserInfo create() {

		MUserInfo mUserInfo = null;

		Object oUserInfo = WebSessionHelper.create().upSession(
				WebConst.CONST_WEB_SESSION_USER);

		if (oUserInfo != null) {
			mUserInfo = (MUserInfo) oUserInfo;
		} else {

			String sCookieUser = WebSessionHelper.create().upCookie(
					WebConst.CONST_WEB_SESSION_USER);

			if (StringUtils.isNotEmpty(sCookieUser)) {
				MDataMap mUserMap = DbUp.upTable("za_userinfo").one(
						"cookie_user", sCookieUser);

				if (mUserMap != null) {
					mUserInfo = inUserInfo(mUserMap);
				}

			}

		}

		return mUserInfo;

	}

	/**
	 * 判断用户是否登录
	 * 
	 * @return
	 */
	public boolean checkUserLogin() {

		boolean bFlagLogin = false;

		MUserInfo mUserInfo = this.create();

		if (mUserInfo != null && mUserInfo.getFlagLogin() == 1) {
			bFlagLogin = true;
		}

		return bFlagLogin;
	}

	/**
	 * 登陆信息初始化并写入session
	 * 
	 * @param mUserData
	 */
	public MUserInfo inUserInfo(MDataMap mUserData) {

		MUserInfo mLoginUserInfo = new MUserInfo();

		if (mUserData.get("flag_enable").equals("1")) {

			mLoginUserInfo.setFlagLogin(1);
			mLoginUserInfo.setLoginName(mUserData.get("user_name"));
			mLoginUserInfo.setUserCode(mUserData.get("user_code"));
			mLoginUserInfo.setRealName(mUserData.get("real_name"));
			mLoginUserInfo.setManageCode(mUserData.get("manage_code"));
			mLoginUserInfo.setCookieUser(mUserData.get("cookie_user"));

			ArrayList<String> aRoleList = new ArrayList<String>();
			for (MDataMap mDataMap : DbUp.upTable("za_userrole").queryByWhere(
					"user_code", mLoginUserInfo.getUserCode())) {
				aRoleList.add(mDataMap.get("role_code"));
			}

			mLoginUserInfo.setUserRole(StringUtils.join(aRoleList,
					WebConst.CONST_SPLIT_LINE));

			MDataMap mMenuMap = new MDataMap();

			// 如果角色数量大于0
			if (aRoleList.size() > 0) {

				List<String> listMenuCode = new ArrayList<String>();

				for (MDataMap mDataMap : DbUp.upTable("za_rolemenu").queryAll(
						"",
						"",
						"role_code in ('" + StringUtils.join(aRoleList, "','")
								+ "')", null)) {

					listMenuCode.add(mDataMap.get("menu_code"));

					mMenuMap.put(mDataMap.get("menu_code"),
							mDataMap.get("menu_code"));

				}

			}

			for (MDataMap mDataMap : DbUp.upTable("za_usermenu").queryByWhere(
					"user_code", mLoginUserInfo.getUserCode())) {
				mMenuMap.put(mDataMap.get("menu_code"),
						mDataMap.get("menu_code"));
			}

			mLoginUserInfo.setUserMenu(StringUtils.join(mMenuMap.keySet(),
					WebConst.CONST_SPLIT_LINE));

			// mLoginUserInfo.setUserMenu(userMenu)

			WebSessionHelper.create().inSession(
					WebConst.CONST_WEB_SESSION_USER, mLoginUserInfo);

			
			
			//插入日志信息
			String sIp = WebSessionHelper.create().upIpaddress();
			WebLogFactory.INSTANCE.addLog(
					"467723120001",
					"system_login",
					bInfo(969912002, mLoginUserInfo.getUserCode(),
							mLoginUserInfo.getLoginName(), sIp));

		}
		return mLoginUserInfo;

	}

	/**
	 * 用户登录检测
	 * 
	 * @param sLoginName
	 * @param sPassword
	 * @return
	 */
	public MWebResult userLogin(String sLoginName, String sPassword) {

		MWebResult mResult = new MWebResult();

		MDataMap mUserInfo = DbUp.upTable("za_userinfo").one("user_name",
				sLoginName);

		if (mResult.upFlagTrue()) {
			// 判断是用户是否存在
			if (mUserInfo == null) {
				mResult.inErrorMessage(969905017);
			}
			// 判断用户是否被冻结
			else if (!StringUtils.equals(mUserInfo.get("flag_enable"), "1")) {
				mResult.inErrorMessage(969905014);
			}
		}

		if (mResult.upFlagTrue()) {

			// 定义最大失败次数
			int iMaxFaieldCount = 5;
			// 定义失败分钟数
			int iMimute = 10;

			int lFaield = Integer.parseInt(mUserInfo.get("failed_count"));

			// 判断最大失败次数
			if (lFaield >= iMaxFaieldCount) {

				// 开始判断最大失败次数
				try {

					Date dFailDate = DateUtils.addMinutes(FormatHelper
							.parseDate(mUserInfo.get("failed_time")), iMimute);

					if (dFailDate.after(new Date())) {
						mResult.inErrorMessage(969905016, iMimute);
					} else {
						lFaield = 0;
					}

				} catch (Exception e) {
					e.printStackTrace();
					mResult.inErrorMessage(969905016, iMimute);
				}
			}

			if (mResult.upFlagTrue()) {

				// 判断如果密码不对则
				if (!SecrurityHelper.MD5Customer(sPassword).equalsIgnoreCase(
						mUserInfo.get("user_password").trim())) {
					// 如果密码不对时 增加冻结次数
					mUserInfo.put("failed_count", String.valueOf(lFaield + 1));

					mUserInfo.put("failed_time", FormatHelper.upDateTime());

					DbUp.upTable("za_userinfo").dataUpdate(mUserInfo,
							"failed_count,failed_time", "uid");
					mResult.inErrorMessage(969905015,
							String.valueOf(iMaxFaieldCount - lFaield - 1));

				}

			}

			// 如果没任何错误 则登陆成功 开始初始化
			if (mResult.upFlagTrue()) {

				String sCookieUser = WebHelper.upUuid() + WebHelper.upUuid();
				mUserInfo.put("cookie_user", sCookieUser);

				mUserInfo.put("login_time", FormatHelper.upDateTime());

				mUserInfo.put("failed_count", "0");

				mUserInfo.put("failed_time", "");

				DbUp.upTable("za_userinfo").dataUpdate(mUserInfo,
						"cookie_user,failed_count,failed_time,login_time",
						"uid");

				inUserInfo(mUserInfo);

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
