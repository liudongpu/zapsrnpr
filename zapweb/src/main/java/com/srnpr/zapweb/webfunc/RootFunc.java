package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webface.IWebFunc;

/**
 * 调用基类
 * 
 * @author srnpr
 * 
 */
public abstract class RootFunc implements IWebFunc {

	/**
	 * 获取Web的字段
	 * @param mFieldMap
	 * @return
	 */
	public MDataMap upWebField(MDataMap mFieldMap) {
		MDataMap mReturn = new MDataMap();

		for (String sKey : mFieldMap.upKeys()) {
			if (StringUtils.startsWith(sKey, WebConst.CONST_WEB_FIELD_NAME)) {
				mReturn.put(StringUtils.substringAfter(sKey,
						WebConst.CONST_WEB_FIELD_NAME), mFieldMap.get(sKey));
			}
		}

		return mReturn;
	}

}
