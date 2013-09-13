package com.srnpr.zapcom.testapi;

import com.srnpr.zapcom.topapi.RootApi;

public class TestApi extends RootApi<TestResult, TestInput> {

	public TestResult Process(TestInput r) {

		TestResult tResult = new TestResult();

		return tResult;

	}

}
