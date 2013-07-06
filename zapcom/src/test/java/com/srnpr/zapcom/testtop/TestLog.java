package com.srnpr.zapcom.testtop;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.topdo.TopTest;

public class TestLog extends TopTest {

	@Test
	public void test() {
		
		String sConfigString=bConfig("zapcom.version");
		
		bLog(0, sConfigString);
		bLog(0, sConfigString);
		bLog(0, bConfig("zapcom.version"));
		
		
		
		bLog(0, bInfo(967901001));
		bLog(0, bInfo(967901002));
	}

}
