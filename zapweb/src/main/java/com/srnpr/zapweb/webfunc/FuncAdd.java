package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

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
 * 
 * @author srnpr
 * 
 */
public class FuncAdd extends RootFunc {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MWebOperate mOperate = WebUp.upOperate(sOperateUid);

		MWebPage mPage = WebUp.upPage(mOperate.getPageCode());

		MDataMap mAddMaps = upWebField(mDataMap, WebConst.CONST_WEB_FIELD_NAME);

		MDataMap mInsertMap = new MDataMap();

		for (MWebField mField : mPage.getPageFields()) {
			if (mAddMaps.containsKey(mField.getColumnName())) {

				String sValue = mAddMaps.get(mField.getColumnName());

				if (StringUtils.isNotEmpty(mField.getRegexValue())) {

					if (mField.getRegexValue().startsWith("+")) {
						if (StringUtils.isEmpty(sValue)) {
							mResult.inErrorMessage(969905003,
									mField.getFieldNote());
							break;
						}
					}

					if (!sValue.matches(mField.getRegexValue())) {
						mResult.inErrorMessage(969905002, mField.getFieldNote());
						break;
					}
				}
				mInsertMap.put(mField.getColumnName(), sValue);
			}
		}

		if (mResult.upFlagTrue()) {

			DbUp.upTable(mPage.getPageTable()).dataInsert(mInsertMap);

		}

		// mResult.setResultMessage("添加成功");

		if (mResult.upFlagTrue()) {
			mResult.setResultMessage(bInfo(969909001));
		}
		return mResult;

	}

}
