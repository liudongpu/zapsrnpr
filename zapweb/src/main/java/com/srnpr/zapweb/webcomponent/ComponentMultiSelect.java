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
		
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
		
		String id = mWebField.getPageFieldName()+"selectid";
		String name = mWebField.getPageFieldName()+"selectname";
		
		
		//,"display","none"
		
		String topParentCode = mSetMap.get("topcode");
		String tableName = mSetMap.get("tablename");
		String fieldvaluename=mSetMap.get("fieldvaluename");
		String fieldtextname=mSetMap.get("fieldtextname");
		String fieldparentname =mSetMap.get("fieldparentname");
		
		
		String showText = this.getShowText(topParentCode, tableName, fieldvaluename, fieldtextname, 
				fieldparentname, id, name, mWebField.getPageFieldValue());
		
		// return sBuilder.toString();
		return showText;
	}

	public String upAddText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap,2);
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
		return upText(mWebField, mDataMap,3);
	}
	
	@Override
	public String upInquireText(MWebField mWebField, MDataMap mDataMap)
	{
		return upText(mWebField,mDataMap,1);
	}
	

	/*
	 * @type 类型 1 查询，2 添加 3 修改
	 */
	private String upText(MWebField mWebField, MDataMap mDataMap,int type)
	{
		
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
		MWebHtml mBaseDivHtml = new MWebHtml("div");
		
		mBaseDivHtml.addChild("hidden", "id", mWebField.getPageFieldName(),
				"name", mWebField.getPageFieldName(), "value",
				mWebField.getPageFieldValue());
		String id = mWebField.getPageFieldName()+"selectid";
		String name = mWebField.getPageFieldName()+"selectname";
		
		
		String hidden2Id =mWebField.getPageFieldName()+"hidden2";
		mBaseDivHtml.addChild("hidden", "id", hidden2Id,
				"name", hidden2Id, "value",
				mWebField.getPageFieldValue());
		
		
		
		mBaseDivHtml.addChild("text", "id", id ,
				"name", name, "value",mWebField.getPageFieldValue(),"style","display:none;");
		
	
		
		//,"display","none"
		
		String topParentCode = mSetMap.get("topcode");
		String tableName = mSetMap.get("tablename");
		String fieldvaluename=mSetMap.get("fieldvaluename");
		String fieldtextname=mSetMap.get("fieldtextname");
		String fieldparentname =mSetMap.get("fieldparentname");
		
		
		boolean ismultilevel = Boolean.parseBoolean(mSetMap.get("ismultilevel"));
		boolean iscaninput = Boolean.parseBoolean(mSetMap.get("iscaninput"));
		String firstviewtext = mSetMap.get("firstviewtext") ;
		boolean mustbelast = Boolean.parseBoolean(mSetMap.get("mustbelast"));
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
		String tempStr = mWebField.getPageFieldValue();
		if(!sencondHiddenValue.equals(""))
			tempStr = sencondHiddenValue;
		
		String dataKeyJson = this.getDataKeyJson(topParentCode, tableName, fieldvaluename, fieldtextname, 
				fieldparentname, id, name, tempStr);
		
		mBaseDivHtml.addChild("script",
				"require(['zapjs/zapjs.combobox'],function(a){" +
						"$(document).ready(function(){$(\"#"+mWebField.getPageFieldName()+"\").comboboxN(" +
						optionjson.toString()+","+dataKeyJson.toString()+")" +
				"})})");

		// return sBuilder.toString();
		return mBaseDivHtml.upString();
	}
	
	private String getShowText(String topParentCode,
			String tableName,
			String fieldvaluename,
			String fieldtextname,
			String fieldparentname,
			String firstSelectId,
			String fristSelectName,String fieldselectValue)
	{
		
		
		ArrayList<CreateSelectModel> listcsm = new ArrayList<CreateSelectModel>();
		
		CreateSelectModel csm = new CreateSelectModel();
		csm.setCurrentSelectValue(fieldselectValue);
		dgParentCSM(csm,listcsm,topParentCode,tableName,fieldvaluename,fieldtextname,fieldparentname,false);

		StringBuffer retValue= new StringBuffer();
		
		int size = listcsm.size();
		int j=1;
		for(int i = size-1;i>=0;i--){
			CreateSelectModel csmitem=listcsm.get(i);
			retValue.append(csmitem.getCurrentSelectValueText());
			if(i!=0)
			{
				retValue.append("-");
			}
		}
		
		return retValue.toString();
		
	}
	
	private String getDataKeyJson(
			String topParentCode,
			String tableName,
			String fieldvaluename,
			String fieldtextname,
			String fieldparentname,
			String firstSelectId,
			String fristSelectName,String fieldselectValue)
	{
		
		
		ArrayList<CreateSelectModel> listcsm = new ArrayList<CreateSelectModel>();
		
		CreateSelectModel csm = new CreateSelectModel();
		csm.setCurrentSelectValue(fieldselectValue);
		dgParentCSM(csm,listcsm,topParentCode,tableName,fieldvaluename,fieldtextname,fieldparentname,true);
		
		int size = listcsm.size();
		ArrayList<CreateSelectModel> newlistcsm = new ArrayList<CreateSelectModel>();
		int j=1;
		for(int i = size-1;i>=0;i--){
			CreateSelectModel csmitem=listcsm.get(i);
			csmitem.setLevel(j);
			if(j == 1)
			{
				csmitem.setId(firstSelectId);
				csmitem.setName(fristSelectName);
				csmitem.setParentid("");
			}
			else
			{
				csmitem.setParentid(firstSelectId);
				firstSelectId=firstSelectId+j;
				fristSelectName = fristSelectName+j;
				csmitem.setId(firstSelectId);
				csmitem.setName(fristSelectName);
			}
			newlistcsm.add(csmitem);
			j++;
		}
		
		JsonHelper<ArrayList<CreateSelectModel>>  jsonh = new JsonHelper<ArrayList<CreateSelectModel>>();
		
		return jsonh.ObjToString(newlistcsm);
	}
	
	
	private void  dgParentCSM(
			CreateSelectModel sourceCsm,
			ArrayList<CreateSelectModel> listcsm,
			String topParentCode,String tableName,String valueName,String textName,String parentidName,boolean needdata)
	{	
		//查询，和 添加 特殊处理
		if(sourceCsm.getCurrentSelectValue().equals(""))
		{
			sourceCsm.setCurrentSelectValue("");
			sourceCsm.setSearchid(topParentCode);
			
			
			if(needdata){
				MStringMap whereMap = new MStringMap();
				whereMap.put("parentId", sourceCsm.getSearchid());
				DbTemplate dt = DbUp.upTable(tableName).upTemplate();
				
				for (Map<String, Object> mItemModel :dt.queryForList("select * from "+tableName+" where "+parentidName+"=:parentId",
						whereMap)) {
					ItemModel im = new ItemModel();
					im.setId(mItemModel.get(valueName).toString());
					im.setText(mItemModel.get(textName).toString());
					
					sourceCsm.getData().add(im);
				}
			}
			
			
			listcsm.add(sourceCsm);
		}
		else
		{
			MDataMap mapOneDataMap = DbUp.upTable(tableName).one(valueName, sourceCsm.getCurrentSelectValue());
			
			if(mapOneDataMap == null)
				return ;
			
			DbTemplate dt = DbUp.upTable(tableName).upTemplate();
			sourceCsm.setSearchid(mapOneDataMap.get(parentidName));
			sourceCsm.setCurrentSelectValueText(mapOneDataMap.get(textName));
			
			if(needdata){
				MStringMap whereMap1 = new MStringMap();
				whereMap1.put("parentId", sourceCsm.getCurrentSelectValue());
				
				List<Map<String, Object>> childList = dt.queryForList("select * from "+tableName+" where "+parentidName+"=:parentId",
						whereMap1);
				
				if(listcsm.size()==0)
				{
					if(childList.size()>0){
						
						CreateSelectModel childCsm = new CreateSelectModel();
						childCsm.setCurrentSelectValue("");
						childCsm.setSearchid(sourceCsm.getCurrentSelectValue());
									
						for (Map<String, Object> mItemModel :childList) {
							ItemModel im = new ItemModel();
							im.setId(mItemModel.get(valueName).toString());
							im.setText(mItemModel.get(textName).toString());
							
							if(childCsm.getCurrentSelectValue().equals(im.getId()))
							{
								im.setSelected("true");
							}
							childCsm.getData().add(im);
						}
						
						listcsm.add(childCsm);
					}
				}
			}
			
			if(needdata){
				MStringMap whereMap = new MStringMap();
				whereMap.put("parentId", sourceCsm.getSearchid());
				
				for (Map<String, Object> mItemModel :dt.queryForList("select * from "+tableName+" where "+parentidName+"=:parentId",
						whereMap)) {
					ItemModel im = new ItemModel();
					im.setId(mItemModel.get(valueName).toString());
					im.setText(mItemModel.get(textName).toString());
					
					if(sourceCsm.getCurrentSelectValue().equals(im.getId()))
					{
						im.setSelected("true");
					}
					sourceCsm.getData().add(im);
				}
			}
			
			
			if(topParentCode.equals(mapOneDataMap.get(parentidName).toString()))
			{
				listcsm.add(sourceCsm);
			}
			else
			{
				listcsm.add(sourceCsm);
				CreateSelectModel csm = new CreateSelectModel();
				csm.setCurrentSelectValue(mapOneDataMap.get(parentidName));
				dgParentCSM(csm,listcsm,topParentCode,tableName,valueName,textName,parentidName,needdata);
			}
		}
	}
}
