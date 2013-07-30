package com.srnpr.zapweb.webfunc;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebField;

/**
 * 调用基类
 * 
 * @author srnpr
 * 
 */
public abstract class RootFunc extends BaseClass implements IWebFunc {

	/**
	 * 获取Web的字段 根据开始标记
	 * 
	 * @param mFieldMap
	 * @param sStartField
	 * @return
	 */
	public MDataMap upWebField(MDataMap mFieldMap, String sStartField) {
		MDataMap mReturn = new MDataMap();

		for (String sKey : mFieldMap.upKeys()) {
			if (StringUtils.startsWith(sKey, sStartField)) {
				mReturn.put(StringUtils.substringAfter(sKey, sStartField),
						mFieldMap.get(sKey));
			}
		}

		return mReturn;
	}

	/**
	 * 字段验证
	 * @param sRegexValue
	 * @param sValue
	 * @return
	 */
	public int recheckInputField(String sRegexValue, String sValue) {

		int iReturn = 1;

		if (StringUtils.isNotEmpty(sRegexValue)) {

			//校验如果是+号开始 则判断是否允许为空
			if (sRegexValue.startsWith("+")) {
				if (StringUtils.isEmpty(sValue)) {
					iReturn = 969905003;
				} else {
					sRegexValue = StringUtils.substringAfter(sRegexValue, "+");
				}
			}

			if (iReturn == 1 && StringUtils.isNotEmpty(sRegexValue)) {
				if (!sValue.matches(sRegexValue)) {
					iReturn = 969905002;

				}
			}
		}

		return iReturn;

	}

}
