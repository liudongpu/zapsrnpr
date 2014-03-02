package com.srnpr.zapweb.webclass;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.NetHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class SysInfo extends BaseClass {

	public MDataMap upSysInfo() {

		MDataMap mDataMap = new MDataMap();

		mDataMap.put("ip", NetHelper.getLocalIP());

		WebSessionHelper webSessionHelper = WebSessionHelper.create();

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
