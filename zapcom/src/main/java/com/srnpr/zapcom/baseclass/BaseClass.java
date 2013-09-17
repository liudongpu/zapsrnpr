package com.srnpr.zapcom.baseclass;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.topdo.TopUp;

/**
 * 基类
 * 
 * @author srnpr
 */
public abstract class BaseClass {

	private Log logger = null;

	/**
	 * @param lInfoId
	 *            默认请写0 否则读取配置文件
	 * @param sParms
	 *            替换参数
	 */
	public void bLogInfo(int iInfoCode, Object... sParms) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.info("[" + String.valueOf(iInfoCode) + "] "
				+ TopUp.upLogInfo(iInfoCode, sParms));
	}

	/**
	 * 错误日志
	 * 
	 * @param lInfoId
	 *            默认请写0 否则读取配置文件
	 * @param sParms
	 *            替换参数
	 */
	public void bLogError(int iInfoCode, Object... sParms) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.error("[" + String.valueOf(iInfoCode) + "] "
				+ TopUp.upLogInfo(iInfoCode, sParms));
	}

	/**
	 * debug日志
	 * 
	 * @param iInfoCode
	 * @param sParms
	 */
	public void bLogDebug(int iInfoCode, Object... sParms) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}
		logger.debug("[" + String.valueOf(iInfoCode) + "] "
				+ TopUp.upLogInfo(iInfoCode, sParms));
	}

	/**
	 * @param sKey
	 *            配置主键
	 * @return 配置内容字符串
	 */
	public String bConfig(String sKey) {
		return TopUp.upConfig(sKey);
	}

	/**
	 * @param lInfoId
	 *            文本编号
	 * @param sParms
	 *            拼接字符串
	 * @return
	 */
	public String bInfo(long iInfoCode, Object... sParms) {

		return FormatHelper.formatString(TopUp.upInfo(iInfoCode), sParms);
	}

}
