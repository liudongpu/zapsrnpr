package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class TopBase {

	private Log logger = null;

	private Log baseLog() {
		
		if (logger == null) {
			
			logger =  LogFactory.getLog(this.getClass());
		}
		return logger;
	
	}
	
	public void bDebug(int lid,Object... oMessage)
	{
		baseLog().debug("[TopBase] "+StringUtils.join(oMessage));
	}
	
	
}
