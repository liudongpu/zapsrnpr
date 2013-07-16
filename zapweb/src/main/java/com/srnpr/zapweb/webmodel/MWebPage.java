package com.srnpr.zapweb.webmodel;

import java.util.List;

public class MWebPage {

	private String pageCode = "";
	private String viewCode = "";

	private String pageTypeAid = "";

	private String pageTemplate = "";
	private String pageName = "";

	private List<MWebOperate> pageOperate;

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	public String getPageTypeAid() {
		return pageTypeAid;
	}

	public void setPageTypeAid(String pageTypeAid) {
		this.pageTypeAid = pageTypeAid;
	}

	public String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public List<MWebOperate> getPageOperate() {
		return pageOperate;
	}

	public void setPageOperate(List<MWebOperate> pageOperate) {
		this.pageOperate = pageOperate;
	}

}
