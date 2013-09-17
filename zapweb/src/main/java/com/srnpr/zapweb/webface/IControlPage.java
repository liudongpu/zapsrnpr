package com.srnpr.zapweb.webface;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;

/**
 * 页面调用类接口
 * 
 * @author srnpr
 * 
 */
public interface IControlPage {

	public void setPageUrl(String pageUrl);

	public void setReqMap(MDataMap reqMap);

	public void setWebPage(MWebPage webPage);
	
	
	public MPageData upChartData();
	
}
