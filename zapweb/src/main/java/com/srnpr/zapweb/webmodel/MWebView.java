package com.srnpr.zapweb.webmodel;

import java.util.ArrayList;
import java.util.List;

public class MWebView {

	private String viewCode = "";

	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	public List<MWebField> getFields() {
		return fields;
	}

	public void setFields(List<MWebField> fields) {
		this.fields = fields; 
	}

	private List<MWebField> fields = new ArrayList<MWebField>();

}
