package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 获取API信息
 * @author srnpr
 *
 */
public class InfoApi extends RootApi<MWebResult, RootInput> {

	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseApi#Process(java.lang.Object, com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult Process(RootInput inputParam, MDataMap mRequestMap) {
		MWebResult mResult = new MWebResult();

		MDataMap mSubMap = mRequestMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		MDataMap mDataApiDataMap = DbUp.upTable("za_apiinfo").one("uid",
				mSubMap.get("uid"));

		MApiModel mApiModel = DefaultApiCache.INSTANCE.upValue(mDataApiDataMap
				.get("class_name"));

		IBaseInput oInput = null;

		try {
			oInput = (IBaseInput) mApiModel.getInputClass().newInstance();

		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		JsonHelper<IBaseInput> jH = new JsonHelper<IBaseInput>();

		String sInput = jH.ObjToString(oInput);

		mDataApiDataMap.put("template_input", sInput);

		mDataApiDataMap.put("javadoc_input", mApiModel.getInputClass()
				.getName());

		mDataApiDataMap.put("javadoc_result", mApiModel.getResultClass()
				.getName());
		
		mDataApiDataMap.put("api_type", mDataApiDataMap.get("api_type_did"));

		mResult.setResultObject(mDataApiDataMap);

		return mResult;
	}

}
