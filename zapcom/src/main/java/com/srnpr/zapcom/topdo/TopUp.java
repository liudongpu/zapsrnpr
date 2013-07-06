package com.srnpr.zapcom.topdo;

public class TopUp {

	private final static TopConfig topConfig = new TopConfig();

	public static String upConfig(String sKey) {
		return topConfig.upValue(sKey);
	}

	private final static TopInfo topInfo = new TopInfo();

	public static String upInfo(long lInfoId) {
		return topInfo.upValue(lInfoId);
	}

}
