package com.srnpr.zapweb.usermodel;

import java.util.ArrayList;
import java.util.List;

public class CreateSelectModel {
	
	
	/**
	 * 控件id-要求整个页面唯一
	 */
	private String id="";
	/**
	 * 控件name-要求整个页面唯一
	 */
	private String name ="";
	/**
	 * 控件level- 要求整个页面,第一个select是 1，其他类推 ，如 省市县  ，省 1，市 2，县 3 等等 。
	 */
	private int level = 1;
	/**
	 * 默认选中的值，在修改的时候使用
	 */
	private String currentSelectValue="";
	
	/**
	 * 默认选中的值，在列表的时候使用
	 */
	private String currentSelectValueText="";
	
	/**
	 * 父控件的id
	 * 如 省市县  ，省 为空，市 省控件的id，县 市控件的id 
	 */
	private String parentid = "";
	/**
	 * 数据库中的 父的id，用于查询的 
	 * 如 省市县  ，省 为空，市 省控件的id，县 市控件的id 
	 */
	private String searchid = "";
	/**
	 * 
	 */
	private List<ItemModel> data= new ArrayList<ItemModel>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCurrentSelectValue() {
		return currentSelectValue;
	}
	public void setCurrentSelectValue(String currentSelectValue) {
		this.currentSelectValue = currentSelectValue;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getSearchid() {
		return searchid;
	}
	public void setSearchid(String searchid) {
		this.searchid = searchid;
	}
	public List<ItemModel> getData() {
		return data;
	}
	public void setData(List<ItemModel> data) {
		this.data = data;
	}
	public String getCurrentSelectValueText() {
		return currentSelectValueText;
	}
	public void setCurrentSelectValueText(String currentSelectValueText) {
		this.currentSelectValueText = currentSelectValueText;
	}
}
