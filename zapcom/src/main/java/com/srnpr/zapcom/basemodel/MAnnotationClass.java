package com.srnpr.zapcom.basemodel;

import java.util.Map;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * 类信息
 * @author srnpr
 *
 */
public class MAnnotationClass {

	/**
	 * 类名称（类的全路径）
	 */
	private String className = "";

	/**
	 * 类的字段
	 */
	private Map<String, MAnnotationField> fields = new ConcurrentHashMap<String, MAnnotationField>();

	/**
	 * 类的中文名称
	 */
	private String title = "";

	/**
	 * 类的备注信息
	 */
	private String remark = "";

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, MAnnotationField> getFields() {
		return fields;
	}

	public void setFields(Map<String, MAnnotationField> fields) {
		this.fields = fields;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
