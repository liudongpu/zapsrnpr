package com.srnpr.zapzero.api;

import com.srnpr.zapcom.basemodel.MStringMap;
import com.srnpr.zapcom.topapi.RootResult;

public class ApiLoadConfigResult extends RootResult {
	/**
	 * 配置文件的字符串表示 <br>
	 * key为文件名 纯文件名 传入的如果是包含目录的名称 会自动去掉目录名称 <br>
	 * value 问文件的内容 纯内容
	 */
	private MStringMap configMap = new MStringMap();

	public MStringMap getConfigMap() {
		return configMap;
	}

	public void setConfigMap(MStringMap configMap) {
		this.configMap = configMap;
	}
}
