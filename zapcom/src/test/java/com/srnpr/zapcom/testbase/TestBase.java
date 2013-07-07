package com.srnpr.zapcom.testbase;

import org.junit.Test;


import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapcom.topdo.TopTest;

public class TestBase extends TopTest {

	/**
	 * 
	 */
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		//new BaseClass().BLog().error("aaa");

		
		TopDir topDir=new TopDir();
		bLog(0,topDir.upTempDir("config"));
		
		
		
		
		
	}

}
