package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapdata.dbsupport.DbTemplate;
import com.srnpr.zapweb.helper.ComponentHelper;
import com.srnpr.zapweb.usermodel.CreateSelectModel;
import com.srnpr.zapweb.usermodel.ItemModel;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;
/**
 * @author yanzj 数据下拉框。
 * 
 *         zw_s_topcode=父code
 *         zw_s_url=url
 *         zw_s_tablename=要操作的select的数据表，目前只支持 单表的  id ,parentid 的树状结构
 * 		   zw_s_fieldvaluename=select的 value字段名称
 *         zw_s_fieldtextname=select的 name字段名称
 *         zw_s_fieldparentname=select的 parent字段名称
 *         zw_s_ismultilevel=是否存在多级  true ，false
 * 		   zw_s_iscaninput=是否能输入 ， true，false 
 *         zw_s_firstviewtext=查询，则为 全部，否则，请为 请选择  
 * 		   zw_s_mustbelast=是否必须选择到最后一级 
 * 
 * 
 * 
 */
public class ComponentMultiSelect extends RootComponent {

	public String upListText(MWebField mWebField, MDataMap mDataMap) {
		
		//System.out.println("upListText======"+mWebField.getPageFieldValue()+"======");
		
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
		
		String id = mWebField.getPageFieldName()+"selectid";
		String name = mWebField.getPageFieldName()+"selectname";
		
		
		//,"display","none"
		
		String topParentCode = mSetMap.get("topcode");
		String tableName = mSetMap.get("tablename");
		String fieldvaluename=mSetMap.get("fieldvaluename");
		String fieldtextname=mSetMap.get("fieldtextname");
		String fieldparentname =mSetMap.get("fieldparentname");
		
		ComponentHelper ch = new ComponentHelper();
		
		String showText = ch.getShowText(topParentCode, tableName, fieldvaluename, fieldtextname, 
				fieldparentname, id, name, mDataMap.get(mWebField.getFieldName()).toString());
		
		// return sBuilder.toString();
		return showText;
	}

	public String upAddText(MWebField mWebField, MDataMap mDataMap) {
		//System.out.println("upAddText======"+mWebField.getPageFieldValue()+"======");
		
		return upText(mWebField, mDataMap,2,mWebField.getPageFieldValue());
	}

	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		//System.out.println("inAdd======"+mWebField.getPageFieldValue()+"======");
		return null;
	}

	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		//System.out.println("inEdit======"+mWebField.getPageFieldValue()+"======");
		return null;
	}

	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		//System.out.println("upEditText======"+mWebField.getPageFieldValue()+"======");
		
		String fieldName=mWebField.getPageFieldName();
		String fieldValue = mWebField.getPageFieldValue();
		
		if(mDataMap.containsKey(fieldName))
			fieldValue = mDataMap.get(mWebField.getPageFieldName()).toString();
		else
			if(mDataMap.containsKey(mWebField.getFieldName()))
				fieldValue = mDataMap.get(mWebField.getFieldName()).toString();
		
		return upText(mWebField, mDataMap,3,fieldValue);
	}
	
	@Override
	public String upInquireText(MWebField mWebField, MDataMap mDataMap)
	{
		//System.out.println("upInquireText======"+mWebField.getPageFieldValue()+"======");
		return upText(mWebField,mDataMap,1,mWebField.getPageFieldValue());
	}
	

	/*
	 * @type 类型 1 查询，2 添加 3 修改
	 */
	private String upText(MWebField mWebField, MDataMap mDataMap,int type,String fieldValue)
	{
		
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
		MWebHtml mBaseDivHtml = new MWebHtml("div");
		
		mBaseDivHtml.addChild("hidden", "id", mWebField.getPageFieldName(),
				"name", mWebField.getPageFieldName(), "value",fieldValue);
		
		String id = mWebField.getPageFieldName()+"selectid";
		String name = mWebField.getPageFieldName()+"selectname";
		
		
		String hidden2Id =mWebField.getPageFieldName()+"hidden2";
		mBaseDivHtml.addChild("hidden", "id", hidden2Id,
				"name", hidden2Id, "value",fieldValue);
		
		
		
		mBaseDivHtml.addChild("text", "id", id ,
				"name", name, "value",fieldValue,"style","display:none;");
		
	
		
		//,"display","none"
		
		String topParentCode = mSetMap.get("topcode");
		String tableName = mSetMap.get("tablename");
		String fieldvaluename=mSetMap.get("fieldvaluename");
		String fieldtextname=mSetMap.get("fieldtextname");
		String fieldparentname =mSetMap.get("fieldparentname");
		
		
		boolean ismultilevel = Boolean.parseBoolean(mSetMap.get("ismultilevel"));
		boolean iscaninput = Boolean.parseBoolean(mSetMap.get("iscaninput"));
		String firstviewtext = mSetMap.get("firstviewtext") ;
		
		boolean mustbelast = false;
		
		if(type == 1)
			mustbelast = false;
		else
			mustbelast = true;
		
		String url= mSetMap.get("url") ;
		
		String parentReplaceName = WebConst.CONST_WEB_FIELD_NAME+fieldparentname;
		
		if(!fieldparentname.equals(""))
			url=url+"?"+parentReplaceName+"="+topParentCode;
		
		
		StringBuffer optionjson = new StringBuffer();
		
		optionjson.append("{");
		
		optionjson.append("\"type\":"+type+",");
		optionjson.append("\"ismultilevel\":"+(ismultilevel?"true":"false")+",");
		optionjson.append("\"iscaninput\":"+(iscaninput?"true":"false")+",");
		optionjson.append("\"firstViewText\":\""+firstviewtext+"\",");
		optionjson.append("\"mustbelast\":"+(mustbelast?"true":"false")+",");
		optionjson.append("\"selectValueName\":\""+mWebField.getPageFieldName()+"\",");
		optionjson.append("\"secondSelectValueName\":\""+hidden2Id+"\",");//notMustBe
		optionjson.append("\"parentReplaceName\":\""+parentReplaceName+"\",");
		optionjson.append("\"url\":\""+url+"\"");
		
		optionjson.append("}");
		
		
		String sencondHiddenValue = (mDataMap.get(hidden2Id)==null?"":mDataMap.get(hidden2Id));
		String tempStr = fieldValue;
		
		
		if(!sencondHiddenValue.equals(""))
			tempStr = sencondHiddenValue;
		ComponentHelper ch = new ComponentHelper();
		String dataKeyJson = ch.getDataKeyJson(topParentCode, tableName, fieldvaluename, fieldtextname, 
				fieldparentname, id, name, tempStr,true);
		
		mBaseDivHtml.addChild("script",
				"require(['zapjs/zapjs.combobox'],function(a){" +
						"$(document).ready(function(){$(\"#"+mWebField.getPageFieldName()+"\").comboboxN(" +
						optionjson.toString()+","+dataKeyJson.toString()+")" +
				"})})");

		// return sBuilder.toString();
		return mBaseDivHtml.upString();
	}
}
