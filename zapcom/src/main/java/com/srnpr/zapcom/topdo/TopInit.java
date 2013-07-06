package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInit;

/**
 * @author srnpr 初始化类
 */
public class TopInit extends BaseClass implements IBaseInit {

	public void init() {

		TopConfig tConfig = new TopConfig();
		tConfig.refresh();

		TopInfo tInfo = new TopInfo();
		tInfo.refresh();

	}

}
