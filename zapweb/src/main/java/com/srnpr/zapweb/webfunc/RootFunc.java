package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webface.IWebFunc;

/**
 * 调用基类
 * 
 * @author srnpr
 * 
 */
public abstract class RootFunc extends BaseClass implements IWebFunc {

	/**
	 * 获取Web的字段
	 * @param mFieldMap
	 * @param sStartField
	 * @return
	 */
	public MDataMap upWebField(MDataMap mFieldMap,String sStartField) {
		MDataMap mReturn = new MDataMap();

		for (String sKey : mFieldMap.upKeys()) {
			if (StringUtils.startsWith(sKey,sStartField)) {
				mReturn.put(StringUtils.substringAfter(sKey,
						sStartField), mFieldMap.get(sKey));
			}
		}

		return mReturn;
	}

}
