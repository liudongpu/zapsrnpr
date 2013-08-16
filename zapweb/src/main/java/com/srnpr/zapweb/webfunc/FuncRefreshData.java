package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.topcall.InitZapweb;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 刷新系统缓存按钮
 * @author srnpr
 *
 */
public class FuncRefreshData extends RootFunc {

	/* (non-Javadoc)
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult=new MWebResult();
		
		
		DbUp.upTable("zd_tables").dataExec("call proc_zw_allview();", new MDataMap());
		DbUp.upTable("zd_tables").dataExec("call proc_zd_allfield();", new MDataMap());
		
		
		mResult.setResultMessage(bInfo(969909002));
		
		return mResult;
	}

}
