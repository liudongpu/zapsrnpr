package com.srnpr.zapzero.api;

import com.srnpr.zapcom.topapi.RootInput;

public class ApiKeepLiveInput extends RootInput {
	/**
	 * 服务编码
	 */
	private String serverCode = "";

	/**
	 * IP地址
	 */
	private String ipAddress = "";

	/**
	 * 运行模式 leader或者follower
	 */
	private String runType = "";

	/**
	 * 本机回调地址
	 */
	private String apiHost = "";
	
	

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getRunType() {
		return runType;
	}

	public void setRunType(String runType) {
		this.runType = runType;
	}

}
