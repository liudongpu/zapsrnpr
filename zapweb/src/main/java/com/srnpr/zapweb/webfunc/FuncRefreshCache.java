package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.topcall.InitZapweb;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncRefreshCache extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		
		
		new InitZapweb().init();
		
		return new MWebResult();
	}

}
