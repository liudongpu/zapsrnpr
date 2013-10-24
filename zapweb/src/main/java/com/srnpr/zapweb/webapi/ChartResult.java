package com.srnpr.zapweb.webapi;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootResult;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;

public class ChartResult extends RootResult {

	private int total = 0;
	
	
	private List<MDataMap> rows=null;
	

	public List<String> getPageFields() {
		return pageFields;
	}

	public void setPageFields(List<String> pageFields) {
		this.pageFields = pageFields;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<MDataMap> getRows() {
		return rows;
	}

	public void setRows(List<MDataMap> rows) {
		this.rows = rows;
	}

	private List<String> pageFields = null;

}
