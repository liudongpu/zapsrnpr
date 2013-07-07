package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseclass.BaseClass;

/**
 * 顶级测试类  所有测试继承该基类
 * @author srnpr
 *
 */
public abstract class TopTest extends BaseClass {

	private static boolean bFlagLoad=false;
	public TopTest()
	{
		if(!bFlagLoad)
		{
			new TopInit().init();
			bFlagLoad=true;
		}
	}
	
}
