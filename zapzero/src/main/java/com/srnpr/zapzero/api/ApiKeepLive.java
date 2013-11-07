package com.srnpr.zapzero.api;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapzero.cache.CacheKeepLive;

/**
 * @author srnpr
 * 
 */
public class ApiKeepLive extends RootApi<ApiKeepLiveResult, ApiKeepLiveInput> {

	public ApiKeepLiveResult Process(ApiKeepLiveInput inputParam,
			MDataMap mRequestMap) {

		ApiKeepLiveResult aResult = new ApiKeepLiveResult();

		String sDateTime = FormatHelper.upDateTime();

		if (CacheKeepLive.getInstance().containsKey(inputParam.getServerCode())) {
			CacheKeepLive.getInstance().upValue(inputParam.getServerCode())
					.setNoticeTime(sDateTime);

		} else {

			inputParam.setNoticeTime(sDateTime);
			CacheKeepLive.getInstance().inElement(inputParam.getServerCode(),
					inputParam);
		}

		return aResult;

	}

}
