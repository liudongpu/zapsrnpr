package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.topapi.RootInput;

/**
 * 简单API调用输入参数
 * @author srnpr
 *
 */
public class SimpleApiInput extends RootInput {

	
	/**
	 * 编码
	 */
	private String inputString="";

	

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	
	
}
