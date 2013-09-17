package com.srnpr.zapweb.webpage;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webface.IControlPage;
import com.srnpr.zapweb.webmodel.MWebPage;

public abstract class RootPage implements IControlPage {

	/**
	 * 页面链接
	 */
	private String pageUrl = "";

	/**
	 * 输入参数
	 */
	private MDataMap reqMap = new MDataMap();

	/**
	 * 页面实体
	 */
	private MWebPage webPage = new MWebPage();

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * 获取请求值
	 * 
	 * @param sKey
	 * @return
	 */
	public String upReqValue(String sKey) {
		return reqMap.get(sKey);
	}

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
}
