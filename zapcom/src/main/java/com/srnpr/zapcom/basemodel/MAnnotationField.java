package com.srnpr.zapcom.basemodel;

/**
 * 字段信息
 * 
 * @author srnpr
 * 
 */
public class MAnnotationField {

	/**
	 * 字段名称（字段的英文名称）
	 */
	private String fieldName = "";

	/**
	 * 是否必须字段(0为非必填 1为必填)
	 */
	private int flagRequire = 0;

	/**
	 * 字段的中文描述
	 */
	private String title = "";

	/**
	 * 字段的备注信息
	 */
	private String remark = "";

	/**
	 * 字段的填写示例
	 */
	private String demo = "";

	/**
	 * 字段的类名称 该字段默认为空 如果属性为结构体 则为结构体的类名称
	 */
	private String fieldClass = "";
	
	/**
	 * 字段的类型
	 */
	private String type="";
	

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFlagRequire() {
		return flagRequire;
	}

	public void setFlagRequire(int flagRequire) {
		this.flagRequire = flagRequire;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(String fieldClass) {
		this.fieldClass = fieldClass;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
