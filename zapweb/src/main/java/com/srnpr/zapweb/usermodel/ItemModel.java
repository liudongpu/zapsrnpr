package com.srnpr.zapweb.usermodel;

public class ItemModel {
	
	/**
	 * option id
	 */
	private String id ="";
	/**
	 * option text
	 */
	private String text ="";
	
	/**
	 * 
	 */
	private String selected="";
	
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
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	
}
