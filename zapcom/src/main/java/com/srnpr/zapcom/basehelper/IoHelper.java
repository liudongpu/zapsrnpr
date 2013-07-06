package com.srnpr.zapcom.basehelper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IoHelper {

	
	
	
	public static void createDir(String sDir)
	{
		try {
			FileUtils.forceMkdir(new File(sDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
