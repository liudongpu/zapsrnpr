package com.srnpr.zapzero.api;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapzero.cache.CacheKeepLive;
import com.srnpr.zapzero.server.ServerInfo;

/**
 * @author srnpr
 * 
 */
public class ApiKeepLive extends RootApi<ApiKeepLiveResult, ServerInfo> {

	public ApiKeepLiveResult Process(ServerInfo inputParam,
			MDataMap mRequestMap) {

		ApiKeepLiveResult aResult = new ApiKeepLiveResult();

		String sDateTime = FormatHelper.upDateTime();

		if (CacheKeepLive.getInstance().containsKey(inputParam.getServerCode())) {
			CacheKeepLive.getInstance().upValue(inputParam.getServerCode())
					.setNoticeTime(sDateTime);

		} else {

			inputParam.setNoticeTime(sDateTime);
			new ApiLoadConfig().addKeepToDB(inputParam);
			CacheKeepLive.getInstance().inElement(inputParam.getServerCode(),
					inputParam);
		}

		return aResult;

	}

}
