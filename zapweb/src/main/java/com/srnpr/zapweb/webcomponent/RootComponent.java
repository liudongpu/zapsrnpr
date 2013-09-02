package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webface.IWebComponent;
import com.srnpr.zapweb.webmodel.MWebField;

public abstract class RootComponent extends BaseClass implements IWebComponent {

	/**
	 * 获取字段名称
	 * @param sColumnName
	 * @return
	 */
	public String upFieldName(String sColumnName) {
		return WebConst.CONST_WEB_FIELD_COMPONENT + sColumnName;
	}

	
	
	/**
	 * 获取设置
	 * @param sUrl
	 * @return
	 */
	public MDataMap upSetMap(String sUrl)
	{
		return new MDataMap().inUrlParams(sUrl).upSubMap(WebConst.CONST_WEB_FIELD_SET);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.srnpr.zapweb.webface.IWebComponent#upInquireText(com.srnpr.zapweb.webmodel.MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upInquireText(MWebField mWebField, MDataMap mDataMap)
	{
		return upEditText(mWebField,mDataMap);
	}
	
	
	
}
