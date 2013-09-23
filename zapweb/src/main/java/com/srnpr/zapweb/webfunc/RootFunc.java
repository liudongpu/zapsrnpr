package com.srnpr.zapweb.webfunc;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
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
				int iReturn = recheckInputField(mField.getRegexValue(), sValue);

				if (iReturn != 1) {
					mResult.inErrorMessage(iReturn, mField.getFieldNote());
					break;
				}
			}

		}

		return mResult;

	}

	/**
	 * 字段验证
	 * 
	 * @param sRegexValue
	 * @param sValue
	 * @return
	 */
	public int recheckInputField(String sRegexValue, String sValue) {

		int iReturn = 1;

		if (StringUtils.isNotEmpty(sRegexValue)) {

			// 校验如果是+号开始 则判断是否允许为空
			if (sRegexValue.startsWith("46992318")) {

				sRegexValue = WebTemp.upTempDataOne("zw_define", "define_name",
						"define_dids", sRegexValue);
			}

			// 判断是否为非空
			if (sRegexValue.startsWith("+")) {
				if (StringUtils.isEmpty(sValue)) {
					iReturn = 969905003;
				} else {
					sRegexValue = StringUtils.substringAfter(sRegexValue, "+");
				}
			}

			// 开始判断正则表达式
			if (iReturn == 1 && StringUtils.isNotEmpty(sRegexValue)) {
				if (!sValue.matches(sRegexValue)) {
					iReturn = 969905002;

				}
			}
		}

		return iReturn;

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
