package com.srnpr.zapweb.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapdata.dbsupport.DbTemplate;
import com.srnpr.zapweb.usermodel.CreateSelectModel;
import com.srnpr.zapweb.usermodel.ItemModel;

public class ComponentHelper {
	
	public String getShowText(String topParentCode,
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
	
	public String getDataKeyJson(
			String topParentCode,
			String tableName,
			String fieldvaluename,
			String fieldtextname,
			String fieldparentname,
			String firstSelectId,
			String fristSelectName,String fieldselectValue,boolean needdata)
	{
		
		
		ArrayList<CreateSelectModel> listcsm = new ArrayList<CreateSelectModel>();
		
		CreateSelectModel csm = new CreateSelectModel();
		csm.setCurrentSelectValue(fieldselectValue);
		dgParentCSM(csm,listcsm,topParentCode,tableName,fieldvaluename,fieldtextname,fieldparentname,needdata);
		
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
	
	
	public void  dgParentCSM(
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
