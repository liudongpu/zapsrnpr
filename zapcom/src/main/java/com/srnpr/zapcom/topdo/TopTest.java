package com.srnpr.zapcom.topdo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.srnpr.zapcom.baseclass.BaseClass;

/**
 * 顶级测试类 所有测试继承该基类
 * 
 * @author srnpr
 * 
 */
public abstract class TopTest extends BaseClass {

	private static boolean bFlagLoad = false;

	public TopTest() {
		if (!bFlagLoad) {
			
			TopDir topDir=new TopDir();
			String sTempDirString= topDir.upTempDir("");
			try {
				FileUtils.deleteDirectory(new File(sTempDirString));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			new TopInit().init();
			bFlagLoad = true;
			
			
			
			
		}
	}

}
