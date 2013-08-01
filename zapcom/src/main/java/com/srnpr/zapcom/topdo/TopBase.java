package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class TopBase {

	private Log logger = null;

	
	
	public void bLogDebug(int lid,Object... oMessage)
	{
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.debug("[TopBase-"+String.valueOf(lid)+"] "+StringUtils.join(oMessage));
	}
	
	public void bLogInfo(int lid,Object... oMessage)
	{
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.info("[TopBase-"+String.valueOf(lid)+"] "+StringUtils.join(oMessage));
	}
	
	
	
}
