package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebField;
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

	/* (non-Javadoc)
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid,MDataMap mDataMap) {
		
		MWebResult mResult=new MWebResult();
		
		
		MWebOperate mOperate=WebUp.upOperate(sOperateUid);
		
		MWebPage mPage=WebUp.upPage(mOperate.getPageCode());
		
		MDataMap mAddMaps=upWebField(mDataMap, WebConst.CONST_WEB_FIELD_NAME);

		MDataMap mInsertMap=new MDataMap();
		
		for(MWebField mField:mPage.getPageFields())
		{
			if(mAddMaps.containsKey(mField.getColumnName()))
			{
				mInsertMap.put(mField.getColumnName(), mAddMaps.get(mField.getColumnName()));
			}
		}
		
		DbUp.upTable(mPage.getPageTable()).dataInsert(mInsertMap);
		
		

		
		mResult.setResultMessage("添加成功");
		

		return mResult;
		
	}

}
