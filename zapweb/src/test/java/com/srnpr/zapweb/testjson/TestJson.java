package com.srnpr.zapweb.testjson;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.bind.WebDataBinder;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.helper.WebDataHelper;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public class TestJson extends TestBase {

	
	public void test() {

		JsonHelper jHelper = new JsonHelper();
		MWebResult mResult = new MWebResult();

		MWebField mField = new MWebField();

		String sJson = jHelper.ObjToString(mResult);

		bLogInfo(0, sJson);

	}

	@Test
	public void testCode() {
		bLogInfo(0, WebDataHelper.upCode("test"));
	
	}

}
