package com.srnpr.zapweb.webfunc;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 平台管理后台修改密码
 * 
 * @author hxd
 * 
 */
public class FuncCmanageAddScore extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();
		//MDataMap mWindowMap = mDataMap.upSubMap("window_change_password_");
//		MUserInfo mUserInfo = UserFactory.INSTANCE.create();
//		// 开始执行操作
//		if (mResult.upFlagTrue()) {
//			WebLogFactory.INSTANCE.addLog("467723120002","change_password",bInfo(969912003, mUserInfo.getUserCode(),mDataMap.get("user_code")));
//			MDataMap mUpdateMap = new MDataMap();
//			mUpdateMap.put("user_password", SecrurityHelper.MD5Customer(mWindowMap.get("new_password")));
//			mUpdateMap.put("user_code", mDataMap.get("user_code"));
//			DbUp.upTable("za_userinfo").dataUpdate(mUpdateMap, "user_password","user_code");
//		}
/*		MDataMap logInsertMap = new MDataMap();
		UUID logUid = UUID.randomUUID();
		logInsertMap.put("uid", logUid.toString().replace("-", ""));
		logInsertMap.put("purchaseorder_code", purchaseorderCode);
		logInsertMap.put("log_info", "创建采购单");
		logInsertMap.put("create_time", DateUtil.getSysDateTimeString());
		logInsertMap.put("create_user", loginName);
		DbUp.upTable("bc_purchase_log").dataInsert(logInsertMap);*/
		
		MDataMap mUpdateMap = new MDataMap();
		try
		{
			mUpdateMap.put("loginpwd", SecrurityHelper.getEncoderByMd5(mDataMap.get("window_change_password_new_password")));
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mUpdateMap.put("agent_code", mDataMap.get("agent_code"));
		DbUp.upTable("agent_information").dataUpdate(mUpdateMap, "loginpwd","agent_code");
		return mResult;

	}
}
