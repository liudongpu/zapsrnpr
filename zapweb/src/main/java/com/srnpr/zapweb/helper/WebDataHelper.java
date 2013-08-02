package com.srnpr.zapweb.helper;

import java.util.Map;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class WebDataHelper {

	public static String upCode(String sCodeStart) {

		Map<String, Object> mResultMap = DbUp.upTable("zw_webcode").dataSqlOne(
				"call proc_zw_getcode(:code);",
				new MDataMap("code", sCodeStart));
		return mResultMap.get("webcode").toString();
	}

}
