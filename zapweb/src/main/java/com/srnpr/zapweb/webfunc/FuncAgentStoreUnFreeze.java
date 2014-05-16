package com.srnpr.zapweb.webfunc;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 平台管理后台代理商商家解冻
 * 
 * @author yanek
 * 
 */
public class FuncAgentStoreUnFreeze extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();
		MDataMap mUpdateMap = new MDataMap();
		mUpdateMap.put("storecode", mDataMap.get("store_code"));
		mUpdateMap.put("oper_status", "1");
		DbUp.upTable("agent_store").dataUpdate(mUpdateMap, "oper_status","storecode");
		return mResult;

	}
}
