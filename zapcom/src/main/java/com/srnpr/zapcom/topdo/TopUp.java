package com.srnpr.zapcom.topdo;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseUp;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MStringMap;

/**
 * 获取类 取该取的
 * @author srnpr
 *
 */
public class TopUp implements IBaseUp {



	/**
	 * 获取配置
	 * @param sKey
	 * @return
	 */
	public static String upConfig(String sKey) {
		
		return TopConfig.Instance.upValue(sKey);
	}

	private final static TopInfo topInfo = new TopInfo();

	/**
	 * 获取定义
	 * @param lInfoId
	 * @return
	 */
	public static String upInfo(long iInfoCode) {
		return topInfo.upValue(iInfoCode);
	}
	
	/** 格式化日志内容
	 * @param iInfoCode
	 * @param sParms
	 * @return
	 */
	public static String upLogInfo(int iInfoCode, Object... sParms)
	{
		return (iInfoCode<1?StringUtils.join(sParms):FormatHelper.formatString(upInfo(iInfoCode), sParms));
	}
	
	/**
	 * 配置
	 */
	private final static ConfigMap configMap=new ConfigMap();
	/**
	 * @param sKey
	 * @return
	 */
	public static MStringMap upConfigMap(String sKey) {
		return configMap.upValue(sKey);
	}
	

}
