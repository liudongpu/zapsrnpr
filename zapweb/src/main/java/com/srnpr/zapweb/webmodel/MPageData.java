package com.srnpr.zapweb.webmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MDataMap;

public class MPageData {

	private int pageCount = -1;
	private int pageIndex = 1;
	private int pageSize = 10;
	private List<List<String>> pageData = null;
	
	
private List<String> pageHead=new ArrayList<String>();
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<List<String>> getPageData() {
		return pageData;
	}

	public void setPageData(List<List<String>> pageData) {
		this.pageData = pageData;
	}

	public List<String> getPageHead() {
		return pageHead;
	}

	public void setPageHead(List<String> pageHead) {
		this.pageHead = pageHead;
	}

}
