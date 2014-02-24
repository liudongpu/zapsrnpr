package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncManageLogin;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 用户登录API
 * @author srnpr
 *
 */
public class UserLoginApi extends RootApi<MWebResult, UserLoginInput> {

	@Override
	public MWebResult Process(UserLoginInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		MDataMap mFuncMap = new MDataMap();
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_name",
				inputParam.getLoginName());
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_pass",
				inputParam.getLoginPass());

		return new FuncManageLogin().funcDo("", mFuncMap);

	}

}
