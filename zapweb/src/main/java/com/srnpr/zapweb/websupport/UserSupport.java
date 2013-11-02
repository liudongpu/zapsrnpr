package com.srnpr.zapweb.websupport;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfactory.WebLogFactory;

public class UserSupport extends BaseClass {

	private MUserInfo userInfo = null;

	/**
	 * 判断登陆状态
	 * 
	 * @return
	 */
	public boolean checkLogin() {
		if (userInfo == null) {
			userInfo = UserFactory.INSTANCE.create();
		}
		return userInfo != null && userInfo.getFlagLogin() == 1;

	}

	/**
	 * 退出登录
	 */
	public String logout() {
		
		checkLogin();
		if(userInfo!=null)
		{
			//插入日志信息
			String sIp = WebSessionHelper.create().upIpaddress();
			WebLogFactory.INSTANCE.addLog(
					"467723120003",
					"system_logout",
					bInfo(969912002, userInfo.getUserCode(),
							userInfo.getLoginName(), sIp));
		}
		
		
		WebSessionHelper.create().inSession(WebConst.CONST_WEB_SESSION_USER,
				null);
		return "";
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public MUserInfo getUserInfo() {
		checkLogin();
		return userInfo;
	}

	/**
	 * 获取用户菜单
	 * 
	 * @param sCode
	 * @return
	 */
	public List<MDataMap> upUserMenu(String sCode) {

		List<MDataMap> lReturnlList = new ArrayList<MDataMap>();
		if (checkLogin()) {
			

			List<MDataMap> lMenuMaps = WebTemp.upTempDataList("za_menu", "",
					"menu_code", "left(menu_code,13)=:menu_code", "menu_code",
					sCode);

			checkLogin();
			String sMenuCode = WebConst.CONST_SPLIT_LINE
					+ getUserInfo().getUserMenu();

			for (MDataMap mMenuMap : lMenuMaps) {

				if (sMenuCode.indexOf(WebConst.CONST_SPLIT_LINE
						+ mMenuMap.get("menu_code")) > -1) {
					lReturnlList.add(mMenuMap);
				}

			}
		}

		// lReturnlList = lMenuMaps;
		return lReturnlList;
	}

}
