package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
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
					&& mSubMap.containsKey("login_pass")) {

			} else {
				mResult.setResultCode(969905013);
			}

		}

		if (mResult.upFlagTrue()) {
			String sLoginName = mSubMap.get("login_name");

			String sPassword = mSubMap.get("login_pass");
			
			
			
			
			
			
			
		}

		return mResult;

	}

}
