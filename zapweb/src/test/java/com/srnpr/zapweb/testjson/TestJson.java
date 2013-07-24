package com.srnpr.zapweb.testjson;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public class TestJson extends TestBase {

	@Test
	public void test() {
		
		JsonHelper jHelper=new JsonHelper();
		MWebResult mResult=new MWebResult();
		
		MWebField mField=new MWebField();
		
		
		
		String sJson= jHelper.ObjToString(mResult);
		
		
		bLogInfo(0, sJson);
		
	}

}
