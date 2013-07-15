package com.srnpr.zapweb.webmodel;

import java.util.ArrayList;
import java.util.List;

public class MWebView {

	private String viewKey = "";

	private String viewCode = "";
	private String viewName = "";

	private String tableName="";
	private List<MWebField> fields = new ArrayList<MWebField>();

	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public List<MWebField> getFields() {
		return fields;
	}

	public void setFields(List<MWebField> fields) {
		this.fields = fields;
	}

	public String getViewKey() {
		return viewKey;
	}

	public void setViewKey(String viewKey) {
		this.viewKey = viewKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
