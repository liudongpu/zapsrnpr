package com.srnpr.zapweb.webapi;

import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.MapSupport;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 获取API列表信息
 * 
 * @author srnpr
 * 
 */
public class ListApi extends RootApi<MWebResult, RootInput> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult Process(RootInput inputParam, MDataMap mRequestMap) {
		MWebResult mResult = new MWebResult();

		String sFieldString = "api_code,api_name,parent_code,uid,api_type_did,project_aid";

		List<MDataMap> listResult = DbUp.upTable("za_apiinfo").queryAll(
				sFieldString, "api_code", "", null);

		mResult.setResultObject(MapSupport.INSTANCE.convertMapsToLists(
				listResult, sFieldString));

		return mResult;
	}

}
