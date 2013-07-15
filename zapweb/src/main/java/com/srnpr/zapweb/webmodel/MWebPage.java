package com.srnpr.zapweb.webmodel;

import java.util.ArrayList;
import java.util.List;

public class MWebPage {

	private String pageCode = "";
	private String viewCode = "";

	private String pageTypeAid = "";

	private String pageTemplate = "";
	private String pageName = "";

	private List<MWebField> fields = new ArrayList<MWebField>();

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

	public List<MWebField> getFields() {
		return fields;
	}

	public void setFields(List<MWebField> fields) {
		this.fields = fields;
	}

}
