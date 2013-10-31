package com.srnpr.zapcom.testapi;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseApi;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basehelper.TestHelper;

public class TestRequest extends TestHelper {

	
	public void test() {
		
		
		
		
		
		
		

		/*
		 * try {
		 * 
		 * String sTextString = "";
		 * 
		 * Class<?> c = Class.forName("com.srnpr.zapcom.testapi.TestApi");
		 * 
		 * // c.getMethods()[0]. Class<?> cReturn = null; Class<?> cInputClass =
		 * null; for (Method method : c.getMethods()) {
		 * 
		 * if (method.getName().equals("Process") && !method.isBridge()) {
		 * cReturn = method.getReturnType(); cInputClass =
		 * method.getParameterTypes()[0];
		 * 
		 * }
		 * 
		 * }
		 * 
		 * TestInput tInput = new TestInput();
		 * 
		 * JsonHelper<TestInput> tHelper = new JsonHelper<TestInput>();
		 * 
		 * sTextString = tHelper.ObjToString(tInput);
		 * 
		 * bLogTest(sTextString);
		 * 
		 * IBaseInput iInput = (IBaseInput) cInputClass.newInstance();
		 * 
		 * JsonHelper<IBaseInput> oHelper = new JsonHelper<IBaseInput>();
		 * 
		 * iInput = oHelper.StringToObj(sTextString, iInput);
		 * 
		 * sTextString = tHelper.ObjToString(tInput);
		 * 
		 * bLogTest(sTextString);
		 * 
		 * IBaseApi tFace1 = (IBaseApi) c.newInstance();
		 * 
		 * IBaseResult iResult = (IBaseResult) tFace1.Process(iInput);
		 * 
		 * JsonHelper<IBaseResult> jsonResult = new JsonHelper<IBaseResult>();
		 * 
		 * sTextString = jsonResult.ObjToString(iResult);
		 * 
		 * bLogTest(sTextString);
		 * 
		 * 
		 * 
		 * } catch (Exception e) { // TODO: handle exception
		 * e.printStackTrace(); }
		 */
	}

}
