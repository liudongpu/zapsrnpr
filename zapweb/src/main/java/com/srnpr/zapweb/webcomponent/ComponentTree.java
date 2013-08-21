package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * @author srnpr 树组件
 * 
 */
public class ComponentTree extends RootComponent {

	public String upListText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap);
	}

	private String upText(MWebField mWebField, MDataMap mDataMap)
	{
		
		MDataMap mSetMap=upSetMap(mWebField.getSourceParam());
		
		StringBuilder sBuilder=new StringBuilder();
		
		
		sBuilder.append("<input type=\"hidden\" name=\""+mWebField.getPageFieldName()+"\"  id=\""+mWebField.getPageFieldName()+"\"  />");
		
		sBuilder.append("<input type=\"button\" class=\"btn btn-small\"  value=\"请选择\"  onclick=\"zapadmin.window_open('')\"/>");
		
		
		
		
		return sBuilder.toString();
	}

	public String upAddText(MWebField mWebField, MDataMap mDataMap) {
		
		return upText(mWebField, mDataMap);
	}

	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap);
	}

	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
