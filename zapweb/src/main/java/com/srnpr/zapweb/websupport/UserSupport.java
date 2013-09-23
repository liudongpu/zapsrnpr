package com.srnpr.zapweb.websupport;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webfactory.UserFactory;

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
	 * 获取用户信息
	 * 
	 * @return
	 */
	public MUserInfo getUserInfo() {
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

		List<MDataMap> lMenuMaps = WebTemp.upTempDataList("za_menu", "",
				"menu_code", "left(menu_code,13)=:menu_code", "menu_code",
				sCode);

		lReturnlList = lMenuMaps;
		return lReturnlList;
	}

}
