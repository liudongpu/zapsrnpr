package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebView;

/**
 * 添加
 * @author srnpr
 *
 */
public class FuncAdd extends RootFunc {

	public MWebResult funcDo(String sOperateUid,MDataMap mDataMap) {
		
		MWebResult mResult=new MWebResult();
		
		
		MWebOperate mOperate=WebUp.upOperate(sOperateUid);
		
		MWebPage mPage=WebUp.upPage(mOperate.getPageCode());
		
		
		
		
		

		
		mResult.setResultMessage("添加成功");
		

		return mResult;
		
	}

}
