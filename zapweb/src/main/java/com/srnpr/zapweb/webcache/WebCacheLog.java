package com.srnpr.zapweb.webcache;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.MapSupport;
import com.srnpr.zapcom.rootclass.CacheDefine;
import com.srnpr.zapcom.rootclass.RootCustomCache;
import com.srnpr.zapweb.webfactory.WebLogFactory;

public class WebCacheLog extends RootCustomCache<String, MDataMap> implements
		IBaseInstance {

	public final static WebCacheLog INSTANCE = new WebCacheLog();

	/**
	 * 添加web访问日志
	 * @param sKey
	 * @param hRequest
	 */
	public void inLog(String sKey, HttpServletRequest hRequest) {

		MDataMap mLogMap = new MDataMap();

		Enumeration<String> eKey = hRequest.getParameterNames();

		while (eKey.hasMoreElements()) {
			String string = eKey.nextElement();
			mLogMap.put("request:" + string,
					StringUtils.join(hRequest.getParameterValues(string), ","));
		}

		String ip = hRequest.getHeader("X-Real-IP");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = hRequest.getHeader("x-forwarded-for");

			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = hRequest.getRemoteAddr();
			}
		}
		mLogMap.put("ip", ip);
		mLogMap.put("time", FormatHelper.upDateTime());

		mLogMap.put("path", hRequest.getServletPath());

		this.inElement(sKey, mLogMap);

	}

}
