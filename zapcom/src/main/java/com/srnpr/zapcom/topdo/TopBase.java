package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class TopBase {

	private Log logger = null;

	/**
	 * 调试日志
	 * 
	 * @param lid
	 * @param oMessage
	 */
	public void bLogDebug(int lid, Object... oMessage) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.debug("[TopBase-" + String.valueOf(lid) + "] "
				+ StringUtils.join(oMessage));
	}

	/**
	 * 正常日志
	 * 
	 * @param lid
	 * @param oMessage
	 */
	public void bLogInfo(int lid, Object... oMessage) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.info("[TopBase-" + String.valueOf(lid) + "] "
				+ StringUtils.join(oMessage));
	}

	/**
	 * 错误日志
	 * 
	 * @param lid
	 * @param oMessage
	 */
	public void bLogError(int lid, Object... oMessage) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.error("[TopBase-" + String.valueOf(lid) + "] "
				+ StringUtils.join(oMessage));
	}

}
