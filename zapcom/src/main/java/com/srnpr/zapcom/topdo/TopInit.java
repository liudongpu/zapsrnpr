package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInit;

/**
 * @author srnpr
 * 初始化类
 */
public class TopInit extends BaseClass implements IBaseInit {

	
	
	public void init() {
		String string="";
		
		BLog().info(System.getProperty("user.home"));
		BLog().error(System.getProperty("java.class.path"));
	}
	
	

}
