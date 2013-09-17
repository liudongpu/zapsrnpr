package com.srnpr.zapweb.testapi;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basehelper.ThreadTestHelper;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webfactory.ApiFactory;

public class TestWebApi extends ThreadTestHelper {

	@Test
	public void test() {

		// ThreadTest(new TestWebApi(), 500);
		
		singleThread(10);

	}

	public void run() {
		
		bLogTest( upThreadName()+ "ad");

	}

}
