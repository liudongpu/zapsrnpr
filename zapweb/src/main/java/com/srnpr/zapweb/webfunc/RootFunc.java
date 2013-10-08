package com.srnpr.zapweb.webfunc;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebCheckHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 调用基类
 * 
 * @author srnpr
 * 
 */
public abstract class RootFunc extends BaseClass implements IWebFunc {

	public MDataMap upFieldMap(MDataMap mDataMap) {

		return mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

	}

	/**
	 * 重新检查输入字段 注意mFieldMap为格式化之后的
	 * 
	 * @param mResult
	 * @param sOperateUid
	 * @param mFieldMap
	 * @return
	 */
	public MWebResult recheckMapField(MWebResult mResult, MWebPage mPage,
			MDataMap mFieldMap) {

		// 循环所有结构
		for (MWebField mField : mPage.getPageFields()) {

			if (mFieldMap.containsKey(mField.getFieldName())) {
				String sValue = mFieldMap.get(mField.getFieldName());

				// 重新校验字段是否正确
				int iReturn = WebCheckHelper.recheckInputField(mField.getRegexValue(), sValue);

				if (iReturn != 1) {
					mResult.inErrorMessage(iReturn, mField.getFieldNote());
					break;
				}
			}

		}

		return mResult;

	}

	

	/**
	 * 重新组装输入字段
	 * 
	 * @param sColumnName
	 * @return
	 */
	public String upFiledName(String sColumnName) {
		return WebConst.CONST_WEB_FIELD_NAME + sColumnName;
	}

}
