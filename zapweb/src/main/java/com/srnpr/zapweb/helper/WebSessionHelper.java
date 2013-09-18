package com.srnpr.zapweb.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.srnpr.zapcom.baseface.IBaseCreate;
import com.srnpr.zapcom.baseface.IBaseHelper;

public class WebSessionHelper implements IBaseHelper, IBaseCreate {

	public static WebSessionHelper create() {
		return new WebSessionHelper();
	}

	protected WebSessionHelper() {
		super();
	}

	private HttpServletRequest request = null;

	public HttpServletRequest upRequest() {
		if (request == null) {
			request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		}

		return request;
	}

	/**
	 * 插入Session
	 * 
	 * @param sKey
	 * @param oValue
	 */
	public void inSession(String sKey, Object oValue) {

		upRequest().getSession().setAttribute(sKey, oValue);

	}

	/**
	 * 获取session
	 * 
	 * @param sKey
	 * @return
	 */
	public Object upSession(String sKey) {

		Object oReturnObject = upRequest().getSession().getAttribute(sKey);

		return oReturnObject;
	}

	/**
	 * 获取Cookie值
	 * 
	 * @param sKey
	 * @return
	 */
	public String upCookie(String sKey) {
		String sReturn = null;
		Cookie[] allCookies = upRequest().getCookies();

		for (Cookie cookie : allCookies) {
			if (cookie.getName().equals(sKey)) {
				sReturn = cookie.getValue();
			}
		}

		return sReturn;
	}

	/**
	 * 获取IP地址
	 * 
	 * @return
	 */
	public String upIpaddress() {

		String ip = upRequest().getHeader("x-forwarded-for");
		/*
		 * if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		 * { ip = request.getHeader("Proxy-Client-IP"); } if (ip == null ||
		 * ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
		 * request.getHeader("WL-Proxy-Client-IP"); }
		 */
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

}