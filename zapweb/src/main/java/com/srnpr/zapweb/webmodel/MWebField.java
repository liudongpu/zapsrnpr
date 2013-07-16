package com.srnpr.zapweb.webmodel;

public class MWebField implements Cloneable {

	private String fieldNote = "";
	private String columnName = "";

	private String sort = "";

	private String fieldTypeAid = "";

	private String pageFieldName = "";

	private String pageFieldValue = "";

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

}
