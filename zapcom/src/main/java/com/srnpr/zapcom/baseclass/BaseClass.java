package com.srnpr.zapcom.baseclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class BaseClass {

	
	private Log logger = null;

	public Log BLog() {
		
		if (logger == null) {
			
			logger =  LogFactory.getLog(this.getClass());
		}
		return logger;
	
	}
	
	public void BInfo()
	{
		
	}
	
	
	
	
	
}
