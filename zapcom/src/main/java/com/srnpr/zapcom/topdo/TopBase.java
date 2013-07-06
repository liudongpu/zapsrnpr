package com.srnpr.zapcom.topdo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TopBase {

	private Log logger = null;

	private Log BLog() {
		
		if (logger == null) {
			
			logger =  LogFactory.getLog(this.getClass());
		}
		return logger;
	
	}
	
	public void BInfo(Object oMessage)
	{
		BLog().info(oMessage);
	}
	
	
}
