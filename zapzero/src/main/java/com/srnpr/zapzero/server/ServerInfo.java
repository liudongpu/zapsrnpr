package com.srnpr.zapzero.server;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.topapi.RootInput;

public class ServerInfo extends RootInput implements IBaseInstance {

	public static ServerInfo INSTANCE = new ServerInfo();

	/**
	 * 服务编码
	 */
	private String serverCode = "";

	/**
	 * IP地址
	 */
	private String ipAddress = "";

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

}
