package com.srnpr.zapweb.webapi;

import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootResult;

/**
 * 顶级配置目录
 * @author srnpr
 *
 */
public class LeaderConfigResult extends RootResult {

	
	private MStringMap configMap=new MStringMap();

	public MStringMap getConfigMap() {
		return configMap;
	}

	public void setConfigMap(MStringMap configMap) {
		this.configMap = configMap;
	}
	
	
}
