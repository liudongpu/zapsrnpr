package com.srnpr.zapweb.webpage;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;

public class ControlPage {

	private MDataMap reqMap = new MDataMap();

	private MWebPage webPage = new MWebPage();

	private final static PageExec pageExec = new PageExec();

	public MDataMap getReqMap() {
		return reqMap;
	}

	public void setReqMap(MDataMap reqMap) {
		this.reqMap = reqMap;
	}

	public MWebPage getWebPage() {
		return webPage;
	}

	public void setWebPage(MWebPage webPage) {
		this.webPage = webPage;
	}
	
	
	public MPageData upChartData()
	{
		return pageExec.chartData(webPage.getViewCode(), reqMap);
	}
	
	

	

}
