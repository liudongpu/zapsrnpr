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
public class BaseClass {

	private Log logger = null;

	/**
	 * @param lInfoId
	 *            默认请写0 否则读取配置文件
	 * @param sParms
	 *            替换参数
	 */
	public void bLog(long lInfoId, String... sParms) {
		if (logger == null) {
			logger = LogFactory.getLog(this.getClass());
		}

		String sLog = "";
		if (lInfoId <1) {
			sLog = StringUtils.join(sParms);
		} else {

			sLog = lInfoId + " " + bInfo(lInfoId, sParms);
		}

		logger.info(sLog);
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
	public String bInfo(long lInfoId, String... sParms) {

		return FormatHelper.formatString(TopUp.upInfo(lInfoId), sParms);
	}

}
