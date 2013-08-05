package com.srnpr.zapweb.webface;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebComponent {

	/**
	 * 获取列表显示
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public String upListText(MWebField mWebField, MDataMap mDataMap);
	
	
	/**
	 * 获取插入页面字符串
	 * @param mField
	 * @param mDataMap
	 * @return
	 */
	public String upAddText(MWebField mField,MDataMap mDataMap);
	
	/**
	 * 执行添加时调用
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap);

}
