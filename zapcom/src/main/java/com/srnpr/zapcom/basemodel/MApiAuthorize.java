package com.srnpr.zapcom.basemodel;

import com.srnpr.zapcom.topapi.RootInput;

public class MApiAuthorize extends RootInput {

	
	
	/**
	 * API密钥
	 */
	private String apiPass="";
	
	/**
	 * APIKey
	 */
	private String apiKey="";
	
	
	/**
	 * API授权
	 */
	private String apiAble="";


	public String getApiPass() {
		return apiPass;
	}


	public void setApiPass(String apiPass) {
		this.apiPass = apiPass;
	}


	public String getApiAble() {
		return apiAble;
	}


	public void setApiAble(String apiAble) {
		this.apiAble = apiAble;
	}


	public String getApiKey() {
		return apiKey;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	
	
	
}
