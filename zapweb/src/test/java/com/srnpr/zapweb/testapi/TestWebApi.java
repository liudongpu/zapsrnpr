//package com.srnpr.zapweb.testapi;
//
//import org.junit.Test;
//
//import com.srnpr.zapcom.basehelper.JsonHelper;
//import com.srnpr.zapcom.basehelper.TestHelper;
//import com.srnpr.zapcom.topapi.RootInput;
//import com.srnpr.zapweb.webfactory.ApiFactory;
//
//public class TestWebApi extends TestHelper implements Runnable {
//
//	
//	public void test() {
//
//		
//		ThreadTest(new TestWebApi(),500);
//		
//	}
//
//	public void run() {
//		
//		
//		RootInput rInput = new RootInput();
//		String sInputJson = new JsonHelper<RootInput>().ObjToString(rInput);
//
//		String sReturnString = ApiFactory.INSTANCE.doProcess(
//				"com.srnpr.zapweb.webfactory.ListApi", sInputJson);
//		
//		bLogTest(upThreadName()+sReturnString);
//
//		
//		
//		
//		
//		/*
//		sReturnString = ApiFactory.INSTANCE.doProcess(
//				"com.srnpr.zapweb.webfactory.ListApi", sInputJson);
//
//		bLogTest(sReturnString);
//
//		sReturnString = ApiFactory.INSTANCE.doProcess(
//				"com.srnpr.zapweb.webfactory.ListApi", sInputJson);
//
//		bLogTest(sReturnString);
//		*/
//	}
//
//}
