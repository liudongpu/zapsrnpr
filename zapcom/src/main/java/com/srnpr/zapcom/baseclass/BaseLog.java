package com.srnpr.zapcom.baseclass;

import org.apache.commons.logging.LogFactory;

import com.srnpr.zapcom.topdo.TopUp;

/**
 * 基本日志类
 * 
 * @author srnpr
 * 
 */
public class BaseLog {

	/**
	 * 记录日志信息
	 * 
	 * @param sClassName
	 * @param iInfoCode
	 * @param sParms
	 */
	public static void LogInfo(String sClassName, int iInfoCode,
			Object... sParms) {
		LogFactory.getLog(sClassName).info(FormatLog(iInfoCode, sParms));
	}

	/**
	 * 格式化日志输出
	 * 
	 * @param iInfoCode
	 * @param sParms
	 * @return
	 */
	public static String FormatLog(int iInfoCode, Object... sParms) {

		return "[" + String.valueOf(iInfoCode) + "] "
				+ TopUp.upLogInfo(iInfoCode, sParms);
	}

}
