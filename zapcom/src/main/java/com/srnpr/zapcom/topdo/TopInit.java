package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseInit;

/**
 * 初始化类
 * 
 * @author srnpr
 */
public class TopInit extends BaseClass implements IBaseInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseInit#init()
	 */
	public void init() {

		initTop();

	}

	/**
	 * 初始化顶级配置
	 */
	private void initTop() {

		TopConfig tConfig = new TopConfig();
		tConfig.refresh();

		TopInfo tInfo = new TopInfo();
		tInfo.refresh();

		ConfigMap configMap = new ConfigMap();
		configMap.refresh();
	}

}
