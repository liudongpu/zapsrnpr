package com.srnpr.zapcom.testserialize;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.SerializeSupport;
import com.srnpr.zapcom.topapi.RootResult;

public class TestSerialize extends TestHelper {

	@Test
	public void test() {

		

		MDataMap mDataMap = new MDataMap();

		mDataMap.put("pString", "abbc");

		mDataMap.put("pLong", "1");

		mDataMap.put("pInt", "2");
		mDataMap.put("pFloat", "3");

		TestClass r2 = new SerializeSupport<TestClass>().serialize(mDataMap,
				new TestClass());

		JsonHelper<TestClass> jTest = new JsonHelper<TestClass>();

		bLogTest(jTest.ObjToString(r2));

		// RootResult r2= support.serialize(mDataMap,RootResult.class);

	}
}
