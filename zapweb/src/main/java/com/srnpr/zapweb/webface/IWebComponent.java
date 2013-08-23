package com.srnpr.zapweb.webface;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public interface IWebComponent {

	/**
	 * 获取列表显示内容
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public String upListText(MWebField mWebField, MDataMap mDataMap);
	
	
	
	public String upInquireText(MWebField mWebField, MDataMap mDataMap);
	

	/**
	 * 获取插入页面字符串
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public String upAddText(MWebField mWebField, MDataMap mDataMap);

	/**
	 * 执行添加逻辑时调用
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap);

	/**
	 * 执行修改逻辑时调用
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap);
	
	
	
	/**
	 * 执行删除逻辑时调用
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap);
	
	

	/**
	 * 
	 * 获取修改时展示内容
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @return
	 */
	public String upEditText(MWebField mWebField, MDataMap mDataMap);

}
