package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncAdd implements IWebFunc {

	public MWebResult funcDo(String sOperateUid,MDataMap mDataMap) {
		
		MWebResult mResult=new MWebResult();
		
		mResult.setResultMessage("添加成功");
		
		
		
		
		
		
		return mResult;
		
	}

}
