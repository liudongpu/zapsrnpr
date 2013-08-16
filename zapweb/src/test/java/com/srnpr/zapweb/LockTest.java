/**
 * 
 */
package com.srnpr.zapweb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.srnpr.zapcom.topdo.TopTest;
import com.srnpr.zapweb.helper.WebHelper;

/**
 * @author Administrator
 *
 */
public class LockTest extends TopTest {

	String uid = "";
	@Before
	public void testLock() {
		uid = WebHelper.addLock("abc,cba", 10);
	}
	
	@Test
	public void testUnLock() {
		System.out.print(WebHelper.unLock(uid));
	}

}
