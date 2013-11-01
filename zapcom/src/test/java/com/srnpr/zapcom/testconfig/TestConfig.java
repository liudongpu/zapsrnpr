package com.srnpr.zapcom.testconfig;

import static org.junit.Assert.*;

import org.junit.Test;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.topdo.TopDir;

public class TestConfig extends BaseClass {

	@Test
	public void test() {

		

		TopDir topDir = new TopDir();

		bLogInfo(0, topDir.upCustomPath("config"));

	}

}
