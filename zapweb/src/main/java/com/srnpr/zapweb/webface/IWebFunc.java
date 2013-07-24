package com.srnpr.zapweb.webface;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebFunc {

	
	/**
	 * 操作处理接口
	 * @param sOperateUid
	 * @param mDataMap
	 * @return
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap);
	
	
}
