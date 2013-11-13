package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfactory.WebLogFactory;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 平台管理后台修改密码
 * 
 * @author jack
 * 
 */
public class FuncCmanageChangePwd extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();
		MDataMap mWindowMap = mDataMap.upSubMap("window_change_password_");
		MUserInfo mUserInfo = UserFactory.INSTANCE.create();
		// 开始执行操作
		if (mResult.upFlagTrue()) {
			WebLogFactory.INSTANCE.addLog("467723120002","change_password",bInfo(969912003, mUserInfo.getUserCode(),mDataMap.get("user_code")));
			MDataMap mUpdateMap = new MDataMap();
			mUpdateMap.put("user_password", SecrurityHelper.MD5Customer(mWindowMap.get("new_password")));
			mUpdateMap.put("user_code", mDataMap.get("user_code"));
			DbUp.upTable("za_userinfo").dataUpdate(mUpdateMap, "user_password","user_code");
		}
		return mResult;

	}
}
