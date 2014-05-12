package com.srnpr.zapcom.basemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市行政区划
 * @author huoqiangshou
 *
 */
public class TempGovDistrict {

	/**
	 * 6位编码：前两位：省 中间两位：市 后两位：区
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String text;
	
	private String state="closed";
	
	/**
	 * 下级机构
	 */
	private List<TempGovDistrict> children = new ArrayList<TempGovDistrict>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TempGovDistrict> getChildren() {
		return children;
	}

	public void setChildren(List<TempGovDistrict> children) {
		this.children = children;
	}

	
	
	
}
