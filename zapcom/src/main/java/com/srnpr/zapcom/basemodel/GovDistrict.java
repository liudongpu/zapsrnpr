package com.srnpr.zapcom.basemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市行政区划
 * @author huoqiangshou
 *
 */
public class GovDistrict {

	/**
	 * 6位编码：前两位：省 中间两位：市 后两位：区
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 下级机构
	 */
	private List<GovDistrict> children = new ArrayList<GovDistrict>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GovDistrict> getChildren() {
		return children;
	}

	public void setChildren(List<GovDistrict> children) {
		this.children = children;
	}
	
	
	
}
