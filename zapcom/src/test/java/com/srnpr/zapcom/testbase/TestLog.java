package com.srnpr.zapcom.testbase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;

public class TestLog extends BaseClass {

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		//new BaseClass().BLog().error("aaa");
		
		BLog().debug("aaa");
		
	}

}
