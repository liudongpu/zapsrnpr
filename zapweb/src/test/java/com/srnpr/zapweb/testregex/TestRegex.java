package com.srnpr.zapweb.testregex;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.srnpr.zapweb.TestBase;

public class TestRegex extends TestBase {

	@Test
	public void test() {
		
		Pattern pattern=Pattern.compile("^[0-9]*$");
		Matcher matcher=pattern.matcher("1a2");
		
String aString="11";

		bLogInfo(0, aString.matches("^\\d{1,99}$"));
		
	}

}
