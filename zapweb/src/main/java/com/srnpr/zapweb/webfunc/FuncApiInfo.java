package com.srnpr.zapweb.webfunc;

import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.ApiFactory;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * API信息获取函数
 * 
 * @author srnpr
 * 
 */
public class FuncApiInfo extends RootFunc {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MDataMap mSubMap = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

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

		mResult.setResultObject(mDataApiDataMap);

		return mResult;
	}

}
