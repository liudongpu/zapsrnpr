package com.srnpr.zapweb.webclass;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.NetHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class SysInfo extends BaseClass {

	public MDataMap upSysInfo() {

		MDataMap mDataMap = new MDataMap();

		mDataMap.put("sysinfo_ip", NetHelper.getLocalIP());

		WebSessionHelper webSessionHelper = WebSessionHelper.create();

		HttpServletRequest request = webSessionHelper.upHttpRequest();

		mDataMap.put("sysinfo_remotehost", webSessionHelper.upHttpRequest()
				.getRemoteHost()
				+ ":"
				+ webSessionHelper.upHttpRequest().getRemotePort());
		
		
		mDataMap.put("sysinfo_client", webSessionHelper.upIpaddress());
		

		mDataMap.put("sysinfo_local", webSessionHelper.upHttpRequest()
				.getLocalAddr()
				+ ":"
				+ webSessionHelper.upHttpRequest().getLocalPort());
		mDataMap.put("sysinfo_requesturi",

		"http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath() // 请求页面或其他地址

				+ (StringUtils.isBlank(request.getQueryString()) ? ""
				: ("?" + request.getQueryString()))

		);

		if (webSessionHelper.upRequest("t").equals("all")) {

			Properties props = System.getProperties();
			Map<String, String> map = System.getenv();

			Enumeration<Object> en = props.keys();
			while (en.hasMoreElements()) {
				String name = en.nextElement().toString();
				String path = props.getProperty(name);
				mDataMap.put(name, path);
			}

			for (String dataKey : map.keySet()) {
				mDataMap.put(dataKey, map.get(dataKey));
			}
		}

		return mDataMap;

	}

}
