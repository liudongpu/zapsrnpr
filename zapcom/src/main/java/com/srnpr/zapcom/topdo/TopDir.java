package com.srnpr.zapcom.topdo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.IoHelper;

/**
 * @author srnpr
 * 路径获取类
 */
public class TopDir extends TopBase {

	private String upZapDir() {
		if (StringUtils.isEmpty(TopConst.CONST_TOP_ZAPDIR)) {
			String sZapDir = System.getProperty("user.home");
			TopConst.CONST_TOP_ZAPDIR = sZapDir + "/" + "zapzoos/zapdir";

		}

		return TopConst.CONST_TOP_ZAPDIR;
	}

	private String upCurrentDir(String sPath) {

		String sReturnString = "";

		if (StringUtils.isEmpty(TopConst.CONST_TOP_CURRENT)) {
			TopConst.CONST_TOP_CURRENT = upZapDir() + "/default";
			
		}

		sReturnString = TopConst.CONST_TOP_CURRENT;

		if(StringUtils.isNotEmpty(sPath))
		{
			sReturnString=sReturnString+"/"+sPath;			
		}
		
		try {
			FileUtils.forceMkdir(new File(sReturnString));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IoHelper.createDir(sReturnString);
		
		return sReturnString;
	}

	
	/**
	 * @param sTempDir
	 * @return
	 */
	public String upTempDir(String sTempDir) {

		if (StringUtils.isEmpty(TopConst.CONST_TOP_DIR_TEMP)) {
			TopConst.CONST_TOP_DIR_TEMP = upCurrentDir("temp");
			BInfo("init TopConst.CONST_TOP_DIR_TEMP=" + TopConst.CONST_TOP_DIR_TEMP);
		}
		
		String sReturnString=TopConst.CONST_TOP_DIR_TEMP+"/"+sTempDir;
		IoHelper.createDir(sReturnString);
		return sReturnString;
	}

}
