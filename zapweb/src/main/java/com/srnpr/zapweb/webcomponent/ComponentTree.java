package com.srnpr.zapweb.webcomponent;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
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
		String sExtendString=WebConst.CONST_WEB_FIELD_EXTEND+"data=\""+mSetMap.get("data")+"\" ";
		sBuilder.append(FormatHelper.formatString(bConfig("zapweb.html_hidden"), mWebField.getPageFieldName(),mWebField.getPageFieldValue(),sExtendString));
		sBuilder.append(FormatHelper.formatString(bConfig("zapweb.html_script"), "require(['zapadmin/js/zapadmin_tree'],function(a){a.init_window('"+mWebField.getPageFieldName()+"');});"));
		sBuilder.append("<input type=\"button\" id=\""+mWebField.getPageFieldName()+"_select\" class=\"btn btn-small\"  value=\"请选择\"  onclick=\"zapadmin.window_url('"+mSetMap.get("show")+"?zw_s_fieldname="+mWebField.getPageFieldName()+"')\"/>");
		
		
		
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
