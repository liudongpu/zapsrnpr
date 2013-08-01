package com.srnpr.zapweb.usermodel;

public class MMenuInfo {

	
	/**
	 * 菜单编号
	 */
	private String menuCode="";
	
	/**
	 * 菜单名称
	 */
	private String menuName="";
	
	/**
	 * 菜单级别
	 */
	private String menuLevel="";
	
	/**
	 * 菜单对应的页面
	 */
	private String menuPage="";
	
	

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuPage() {
		return menuPage;
	}

	public void setMenuPage(String menuPage) {
		this.menuPage = menuPage;
	}
	
	
}
