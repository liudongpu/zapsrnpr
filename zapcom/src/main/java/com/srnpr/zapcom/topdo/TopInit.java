package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCache;
import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.basemodel.MStringMap;

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
	public synchronized void init() {

		initTop();
		initClass();

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

	private void initClass() {

		String sConfigName = "zapcom.initclass";

		MStringMap mStringMap = TopUp.upConfigMap(sConfigName);

		for (String sClassName : mStringMap.values()) {

			if (!StringUtils.isEmpty(sClassName)) {
				try {

					Class<?> cClass = ClassUtils.getClass(sClassName);
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IBaseInit init = (IBaseInit) cClass.newInstance();
						init.init();
					}
				} catch (Exception e) {

					bLogInfo(967905001, sClassName);
					e.printStackTrace();

				}
			}

		}
	}

}
