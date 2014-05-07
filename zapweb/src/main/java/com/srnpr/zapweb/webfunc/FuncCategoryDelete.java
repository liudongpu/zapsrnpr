package com.srnpr.zapweb.webfunc;

import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncCategoryDelete extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();
		MWebOperate mOperate = WebUp.upOperate(sOperateUid);
		MWebPage mPage = WebUp.upPage(mOperate.getPageCode());
		MDataMap mDelMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);
		if (mResult.upFlagTrue()) {
			if (mDelMaps.containsKey("uid")) {
				MDataMap mThisMap=null;
				// 循环所有结构
				for (MWebField mField : mPage.getPageFields()) {
					if (mField.getFieldTypeAid().equals("104005003")) {
						if(mThisMap==null)
						{
							mThisMap=DbUp.upTable(mPage.getPageTable()).one("uid",mDelMaps.get("uid"));
						}
						WebUp.upComponent(mField.getSourceCode()).inDelete(mField,mThisMap);
					}
				}
				List<Map<String, Object>> exlist = DbUp.upTable(mPage.getPageTable()).dataSqlList("select pr.product_code from pc_productcategory_rel pr LEFT JOIN pc_categoryinfo pc on pr.category_code=pc.category_code where pc.uid=:uid", mDelMaps);
				if(exlist.size()>0){
					mResult.setResultCode(969912007);
					mResult.setResultMessage(bInfo(969912007));
				}else {
					DbUp.upTable(mPage.getPageTable()).delete("uid",mDelMaps.get("uid"));
				}
			}
		}
		if (mResult.upFlagTrue()) {
			mResult.setResultMessage(bInfo(969909001));
		}
		return mResult;
	}

}
