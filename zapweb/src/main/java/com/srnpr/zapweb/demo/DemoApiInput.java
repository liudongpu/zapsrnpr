package com.srnpr.zapweb.demo;

import com.srnpr.zapcom.topapi.RootInput;

/**
 * API调用测试类
 * @author srnpr
 *
 */
public class DemoApiInput extends RootInput {

	
	/**
	 * demo输入字段一号
	 */
	private String demoInputFirst="";
	
	
	/**
	 * demo输入数字
	 */
	private int demoInputInt=0;
	

	public String getDemoInputFirst() {
		return demoInputFirst;
	}

	public void setDemoInputFirst(String demoInputFirst) {
		this.demoInputFirst = demoInputFirst;
	}

	public int getDemoInputInt() {
		return demoInputInt;
	}

	public void setDemoInputInt(int demoInputInt) {
		this.demoInputInt = demoInputInt;
	}
	
	
	
	
}
