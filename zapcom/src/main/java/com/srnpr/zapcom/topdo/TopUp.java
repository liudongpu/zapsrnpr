package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.basemodel.MStringMap;

/**
 * 获取类 取该取的
 * @author srnpr
 *
 */
public class TopUp {

	private final static TopConfig topConfig = new TopConfig();

	/**
	 * 获取配置
	 * @param sKey
	 * @return
	 */
	public static String upConfig(String sKey) {
		return topConfig.upValue(sKey);
	}

	private final static TopInfo topInfo = new TopInfo();

	/**
	 * 获取定义
	 * @param lInfoId
	 * @return
	 */
	public static String upInfo(long lInfoId) {
		return topInfo.upValue(lInfoId);
	}
	
	
	
	private final static ConfigMap configMap=new ConfigMap();
	public static MStringMap upConfigMap(String sKey) {
		return configMap.upValue(sKey);
	}
	

}
