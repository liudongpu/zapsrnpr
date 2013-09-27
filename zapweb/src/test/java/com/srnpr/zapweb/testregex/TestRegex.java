package com.srnpr.zapweb.testregex;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.webmodel.MWebResult;

public class TestRegex extends TestHelper {

	@Test
	public void test() {

		StringBuffer sBuffer = new StringBuffer();
		
		sBuffer.append("zapjs.zw.regex={");

		List<String> lStrings = new ArrayList<String>();
		
		

		for (MDataMap mMap : DbUp.upTable("zw_define").queryByWhere(
				"parent_did", "46992318")) {

			lStrings.add("r_" + mMap.get("define_dids") + ":{reg:\""
					+ mMap.get("define_name") + "\",name:\""
					+ mMap.get("define_note") + "\"}");

			// sBuffer.append("[]");

		}
		
		sBuffer.append(StringUtils.join(lStrings,",\r\n"));
		
		sBuffer.append("}");
		
		

		System.out.println(sBuffer.toString());

	}

}
