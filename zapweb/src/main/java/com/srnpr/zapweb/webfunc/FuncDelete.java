package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncDelete extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();

		MWebOperate mOperate = WebUp.upOperate(sOperateUid);

		MWebPage mPage = WebUp.upPage(mOperate.getPageCode());

		MDataMap mDelMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		if (mResult.upFlagTrue()) {
			if (mDelMaps.containsKey("uid")) {

				DbUp.upTable(mPage.getPageTable()).delete("uid",
						mDelMaps.get("uid"));

			}
		}

		if (mResult.upFlagTrue()) {
			mResult.setResultMessage(bInfo(969909001));
		}

		return mResult;
	}

}
