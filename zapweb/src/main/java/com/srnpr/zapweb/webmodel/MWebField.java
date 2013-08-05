package com.srnpr.zapweb.webmodel;

public class MWebField implements Cloneable {

	/**
	 * 字段中文名
	 */
	private String fieldNote = "";
	/**
	 * 数据库名称
	 */
	private String columnName = "";

	/**
	 * 排序
	 */
	private String sort = "";

	/**
	 * 字段类型
	 */
	private String fieldTypeAid = "";

	/**
	 * 字段名称
	 */
	private String pageFieldName = "";

	/**
	 * 页面元素值
	 */
	private String pageFieldValue = "";

	/**
	 * 正则表达式
	 */
	private String regexValue = "";

	/**
	 * 查询类型
	 */
	private String queryTypeAid = "";

	/**
	 * 数据源编码
	 */
	private String sourceCode = "";

	/**
	 * 数据源参数
	 */
	private String sourceParam = "";

	/**
	 * 默认值 创建时调用该逻辑判断
	 */
	private String defaultValue = "";

	public String getFieldNote() {
		return fieldNote;
	}

	public void setFieldNote(String fieldNote) {
		this.fieldNote = fieldNote;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getFieldTypeAid() {
		return fieldTypeAid;
	}

	public void setFieldTypeAid(String fieldTypeAid) {
		this.fieldTypeAid = fieldTypeAid;
	}

	public MWebField clone() {

		MWebField o = null;
		try {
			o = (MWebField) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String getPageFieldName() {
		return pageFieldName;
	}

	public void setPageFieldName(String pageFieldName) {
		this.pageFieldName = pageFieldName;
	}

	public String getPageFieldValue() {
		return pageFieldValue;
	}

	public void setPageFieldValue(String pageFieldValue) {
		this.pageFieldValue = pageFieldValue;
	}

	public String getRegexValue() {
		return regexValue;
	}

	public void setRegexValue(String regexValue) {
		this.regexValue = regexValue;
	}

	public String getQueryTypeAid() {
		return queryTypeAid;
	}

	public void setQueryTypeAid(String queryTypeAid) {
		this.queryTypeAid = queryTypeAid;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceParam() {
		return sourceParam;
	}

	public void setSourceParam(String sourceParam) {
		this.sourceParam = sourceParam;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
