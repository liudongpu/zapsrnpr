package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * @author yanzj 数据下拉框之列表样式,添加商品专用
 * 
 *         zw_s_topcode=父code
 *         zw_s_url=url
 *         zw_s_tablename=要操作的select的数据表，目前只支持 单表的  id ,parentid 的树状结构
 * 		   zw_s_fieldvaluename=select的 value字段名称
 *         zw_s_fieldtextname=select的 name字段名称
 *         zw_s_fieldparentname=select的 parent字段名称
 *         zw_s_width = 宽度 正整数
 * 		   zw_s_height = 高度  正整数
 *         zw_s_classid=css 样式   
 * 		   zw_s_maxlevel=3最大级数
 * 		   zw_s_lastselectchange=改变事件
 * 
 * 
 */
public class ComponentSelectList extends RootComponent {

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
		return upText(mWebField, mDataMap,2,mWebField.getPageFieldValue());
	}

	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {
		// TODO Auto-generated method stub
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
		
		String topParentCode = mSetMap.get("topcode");
		String tableName = mSetMap.get("tablename");
		String fieldvaluename=mSetMap.get("fieldvaluename");
		String fieldtextname=mSetMap.get("fieldtextname");
		String fieldparentname =mSetMap.get("fieldparentname");
		
		
		
		/*         zw_s_width = 宽度 正整数
		 * 		   zw_s_height = 高度  正整数
		 *         zw_s_classid=css 样式   
		 * 		   zw_s_maxlevel=3最大级数
		 * */
		
		int width = Integer.parseInt(mSetMap.get("width"));
		int height = Integer.parseInt(mSetMap.get("height"));
		String classid = mSetMap.get("classid").toString();
		int maxlevel = Integer.parseInt(mSetMap.get("maxlevel"));
		String lastselectchange = mSetMap.get("lastselectchange").toString();
		String url= mSetMap.get("url") ;
		
		String parentReplaceName = WebConst.CONST_WEB_FIELD_NAME+fieldparentname;
		
		if(!fieldparentname.equals(""))
			url=url+"?"+parentReplaceName+"="+topParentCode;
		
		
		StringBuffer optionjson = new StringBuffer();
		
		optionjson.append("{");
		
		optionjson.append("\"type\":"+type+",");
		optionjson.append("\"maxlevel\":"+maxlevel+",");
		optionjson.append("\"width\":"+width+",");
		optionjson.append("\"height\":"+height+",");
		optionjson.append("\"classid\":\""+classid+"\",");
		optionjson.append("\"selectValueName\":\""+mWebField.getPageFieldName()+"\",");//notMustBe
		if(lastselectchange.equals(""))
			optionjson.append("\"lastselectchange\":\"\",");
		else
			optionjson.append("\"lastselectchange\":"+lastselectchange+",");
		
		optionjson.append("\"parentReplaceName\":\""+parentReplaceName+"\",");
		optionjson.append("\"url\":\""+url+"\"");
		
		optionjson.append("}");
		
		String tempStr = fieldValue;
		
		ComponentHelper ch = new ComponentHelper();
		String dataKeyJson = ch.getDataKeyJson(topParentCode, tableName, fieldvaluename, fieldtextname, 
				fieldparentname, id, name, tempStr,true);
		
		mBaseDivHtml.addChild("script",
				"require(['zapjs/zapjs.comboboxc'],function(a){" +
						"$(document).ready(function(){$(\"#"+mWebField.getPageFieldName()+"\").comboboxC(" +
						optionjson.toString()+","+dataKeyJson.toString()+")" +
				"})})");

		// return sBuilder.toString();
		return mBaseDivHtml.upString();
	}

}
