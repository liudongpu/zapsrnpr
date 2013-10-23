package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.PageExec;
import com.srnpr.zapweb.webpage.PageProcess;

public class ChartApi extends RootApi<MWebResult, ChartInput> {

	private static final PageExec page_exec = new PageExec();

	public MWebResult Process(ChartInput inputParam, MDataMap mRequestMap) {

		MWebResult mResult = new MWebResult();

		MWebPage webPage = WebUp.upPage(inputParam.getPageCode());

		mResult.setResultObject(page_exec.upChartData(webPage, mRequestMap,
				new MDataMap()));

		return mResult;

	}

}
