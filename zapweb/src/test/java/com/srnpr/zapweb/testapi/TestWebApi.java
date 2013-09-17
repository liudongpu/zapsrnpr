package com.srnpr.zapweb.testapi;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basehelper.ThreadTestHelper;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webfactory.ApiFactory;

public class TestWebApi extends ThreadTestHelper {
	/**
	 * 测试专用
	 */
	@Test
	public void test() {
		// ThreadTest(new TestWebApi(), 500);
		//多个线程调用测试示例   
		//该方法会创建10个线程调用run方法
		muliThread(10);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		//执行的测试业务逻辑
		bLogTest(upThreadName() + "ad");
	}
}
