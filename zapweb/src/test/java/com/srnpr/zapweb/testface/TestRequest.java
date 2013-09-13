package com.srnpr.zapweb.testface;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapweb.webface.IWebFace;
import com.srnpr.zapweb.webface.IWebInput;
import com.srnpr.zapweb.webface.IWebResult;

public class TestRequest extends TestHelper {

	@Test
	public void test() {

		try {

			String sTextString = "";

			Class<?> c = Class.forName("com.srnpr.zapweb.testface.TestFace1");

			// c.getMethods()[0].
			Class<?> cReturn = null;
			Class<?> cInputClass = null;
			for (Method method : c.getMethods()) {

				if (method.getName().equals("DoProcess") && !method.isBridge()) {
					cReturn = method.getReturnType();
					cInputClass = method.getParameterTypes()[0];
				
				}

			}

			TestInput tInput = new TestInput();
			tInput.setField1("abc");

			JsonHelper<TestInput> tHelper = new JsonHelper<TestInput>();

			sTextString = tHelper.ObjToString(tInput);

			bLogTest(sTextString);

			IWebInput iInput = (IWebInput) cInputClass.newInstance();

			JsonHelper<IWebInput> oHelper = new JsonHelper<IWebInput>();

			iInput = oHelper.StringToObj(sTextString, iInput);

			sTextString = tHelper.ObjToString(tInput);

			bLogTest(sTextString);

			IWebFace tFace1 = (IWebFace) c.newInstance();

			IWebResult iResult = (IWebResult) tFace1.DoProcess(iInput);

			JsonHelper<IWebResult> jsonResult = new JsonHelper<IWebResult>();

			sTextString = jsonResult.ObjToString(iResult);

			bLogTest(sTextString);

			/*
			 * Object object = new Object();
			 * 
			 * JsonHelper<Object> oHelper = new JsonHelper<Object>();
			 * 
			 * oHelper.StringToObj(sTextString, object);
			 * 
			 * IWebFace tFace1 = new TestFace1();
			 * 
			 * tFace1.Call(object);
			 */
			// IWebFace tFace1=new TestFace1();

			// tFace1.Call()

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
