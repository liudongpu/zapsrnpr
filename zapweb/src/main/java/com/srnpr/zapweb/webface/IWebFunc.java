package com.srnpr.zapweb.webface;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebFunc {

	
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap);
	
	
}
