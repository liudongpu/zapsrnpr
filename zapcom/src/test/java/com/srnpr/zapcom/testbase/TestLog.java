package com.srnpr.zapcom.testbase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.topdo.TopConfig;
import com.srnpr.zapcom.topdo.TopDir;

public class TestLog extends BaseClass {

	/**
	 * 
	 */
	@Test
	public void test() {
		//fail("Not yet implemented");
		
		//new BaseClass().BLog().error("aaa");
		
		
		
		
		TopDir topDir=new TopDir();
		BLog().debug(topDir.upTempDir("config"));
		
		
		TopConfig tConfig=new TopConfig();
		
		tConfig.inElement("aa", "xx");
		
		
		BLog().info(tConfig.upValue("aa"));
		
		
	}

}
