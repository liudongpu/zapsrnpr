package com.srnpr.zapweb.websupport;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;

public class ApiCallSupport<TInput extends IBaseInput, TResult extends IBaseResult>
		extends BaseClass {

	/**
	 * 调用API
	 * 
	 * @param sAddress
	 *            API地址
	 * @param sTarget
	 *            API目标
	 * @param sApiKey
	 * @param sApiPass
	 * @param input
	 * @param tResult
	 * @return
	 * @throws Exception
	 */
	public TResult doCallApi(String sAddress, String sTarget, String sApiKey,
			String sApiPass, TInput input, TResult tResult) throws Exception {

		if (sAddress.endsWith("/")) {
			sAddress = sAddress + sTarget;
		}

		JsonHelper<TInput> jsonHelper = new JsonHelper<TInput>();

		String sInputString = jsonHelper.ObjToString(input);

		String sTimeSpan = FormatHelper.upDateTime();

		String sSource = sTarget + sApiKey + sInputString + sTimeSpan
				+ sApiPass;
		String sSec = SecrurityHelper.MD5(sSource);

		
		MDataMap mDataMap = new MDataMap();
		mDataMap.put("api_key", sApiKey);
		mDataMap.put("api_input", sInputString);
		mDataMap.put("api_target", sTarget);
		mDataMap.put("api_secret", sSec);
		mDataMap.put("api_timespan", sTimeSpan);

		String sCallString = WebClientSupport.upPost(sAddress, mDataMap);

		//bLogDebug(0, sCallString);

		JsonHelper<TResult> jsonHelperResult = new JsonHelper<TResult>();

		tResult = jsonHelperResult.StringToObjExp(sCallString, tResult);

		return tResult;

	}

}
