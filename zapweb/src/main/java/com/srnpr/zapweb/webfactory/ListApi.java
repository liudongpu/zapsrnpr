package com.srnpr.zapweb.webfactory;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapcom.topapi.RootResult;

public class ListApi extends RootApi<RootResult, RootInput> {

	public RootResult Process(RootInput r, MDataMap mRequestMap) {

		RootResult result = new RootResult();

		
		result.setResultMessage(Double.toString( Math.random()));
		
		return result;

	}

}
