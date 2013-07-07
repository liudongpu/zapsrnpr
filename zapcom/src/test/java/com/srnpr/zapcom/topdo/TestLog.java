package com.srnpr.zapcom.topdo;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.srnpr.zapcom.TestBase;

public class TestLog extends TestBase {

	@Test
	public void test() {
		
		
		TopUp topUp=new TopUp();
		
		bLog(0, topUp.upConfigMap("zapcom.init").get("zapcom"));
		
		
	}

}
