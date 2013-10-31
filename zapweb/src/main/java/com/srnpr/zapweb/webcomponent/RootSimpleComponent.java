package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 基本组件类 其中参数类型 1为add 3为chart 5为edit 4为delete
 * 
 * @author srnpr
 * 
 */
public abstract class RootSimpleComponent extends RootComponent {

	/**
	 * 显示内容
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @param iType
	 *            其中参数类型 1为add 3为chart 5为edit 4为delete
	 * @return
	 */
	public abstract String upText(MWebField mWebField, MDataMap mDataMap,
			int iType);

	/**
	 * 数据操作
	 * 
	 * @param mWebField
	 * @param mDataMap
	 * @param iType
	 *            其中参数类型 1为add 3为chart 5为edit 4为delete
	 * @return
	 */
	public abstract MWebResult inDo(MWebField mWebField, MDataMap mDataMap,
			int iType);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upListText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upListText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap, 3);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upAddText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upAddText(MWebField mWebField, MDataMap mDataMap) {

		return upText(mWebField, mDataMap, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#inAdd(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		return inDo(mWebField,
				mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME), 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#inEdit(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {

		return inDo(mWebField,
				mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME), 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#upEditText(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap, 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.srnpr.zapweb.webface.IWebComponent#inDelete(com.srnpr.zapweb.webmodel
	 * .MWebField, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap) {
		return inDo(mWebField, mDataMap, 4);
	}
	
	
	public String[] upRegexString(MWebField mField)
	{
		String[] sRegStrings=new String[4];
		
		
		
		
		if(StringUtils.isNotBlank(mField.getRegexValue()))
		{
			sRegStrings[0]=WebConst.CONST_WEB_FIELD_ATTR+"regex_id";
			sRegStrings[1]=mField.getRegexValue();
			sRegStrings[2]=WebConst.CONST_WEB_FIELD_ATTR+"regex_title";
			sRegStrings[2]=mField.getFieldNote();
		}
		
		
		return sRegStrings;
		
		
	}
	
	

}
