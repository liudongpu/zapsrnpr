package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.topcall.InitZapweb;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 刷新系统缓存按钮
 * @author srnpr
 *
 */
public class FuncRefreshCache extends RootFunc {

	/* (non-Javadoc)
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		
		
		new InitZapweb().init();
		
		return new MWebResult();
	}

}
