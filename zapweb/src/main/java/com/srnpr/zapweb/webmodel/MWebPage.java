package com.srnpr.zapweb.webmodel;

import java.util.List;

import com.srnpr.zapcom.baseannotation.ZapcomApi;

public class MWebPage {

	private String pageCode = "";
	private String viewCode = "";

	private String pageTypeAid = "";

	private String pageTemplate = "";
	private String pageName = "";
	
	private String pageTable="";
	
	private String dataScope="";
	
	
	

	@ZapcomApi(value="页面按钮",remark="页面操作的所有按钮")
	private List<MWebOperate> pageOperate;
	
	@ZapcomApi(value="页面字段",remark="字段的定义")
	private List<MWebField> pageFields;
	

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

	public List<MWebField> getPageFields() {
		return pageFields;
	}

	public void setPageFields(List<MWebField> pageFields) {
		this.pageFields = pageFields;
	}

	public String getPageTable() {
		return pageTable;
	}

	public void setPageTable(String pageTable) {
		this.pageTable = pageTable;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	

}
