package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncTreeEdit extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mResult = new MWebResult();

		MWebOperate mOperate = WebUp.upOperate(sOperateUid);
		MWebPage mPage = WebUp.upPage(mOperate.getPageCode());
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);
		MDataMap mInsertMap = new MDataMap();
		// 定义组件判断标记
		boolean bFlagComponent = false;
		// 循环所有结构
		for (MWebField mField : mPage.getPageFields()) {
			if (mField.getFieldTypeAid().equals("104005003")) {
				bFlagComponent = true;
			}
			if (mAddMaps.containsKey(mField.getColumnName())) {
				String sValue = mAddMaps.get(mField.getColumnName());
				// 重新校验字段是否正确
				int iReturn = recheckInputField(mField.getRegexValue(), sValue);
				if (iReturn != 1) {
					mResult.inErrorMessage(iReturn, mField.getFieldNote());
					break;
				}
				mInsertMap.put(mField.getColumnName(), sValue);
			}
		}
		if (mResult.upFlagTrue()) {
			DbUp.upTable(mPage.getPageTable())
					.dataUpdate(mInsertMap, "", "uid");
			if (bFlagComponent) {
				for (MWebField mField : mPage.getPageFields()) {
					if (mField.getFieldTypeAid().equals("104005003")) {
						WebUp.upComponent(mField.getSourceCode()).inEdit(
								mField, mDataMap);
					}
				}
			}
		}
		if (mResult.upFlagTrue()) {
			mResult.setResultMessage(bInfo(969909001));
		}
		return mResult;
	}

}
