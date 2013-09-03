package com.srnpr.zapweb.testbase;

import static org.junit.Assert.*;

import org.junit.Test;


import com.srnpr.zapcom.topdo.TopTest;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.TestBase;
import com.srnpr.zapweb.helper.WebHelper;

public class TestClass extends TestBase {

	
	@Test
	public void testRefresh() {
		/*//fail("Not yet implemented");
		
		
		bLogInfo(0, bConfig("zapcom.version"));
		
		String sConfigString=bConfig("cmanage.configname");
		
		//假设info配置中定义为：
		//911122333=这个项目名称{0}设置为{1}，请操作
		String sInfoString=bInfo(911122333, "a","b");
		//则sInfoString输出为
		//这个项目名称a设置为b，请操作
		
		//DbUp.upTable("aa").
*/		
		
		
		
		//获取系统编码
		//获取单据号格式
		String sCode=WebHelper.upCode("DD");
		bLogInfo(0, sCode);
		//输出结果为：DD130903100009
		
		
		
		
		
	}

}
