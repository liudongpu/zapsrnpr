package com.srnpr.zapcom.topdo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.IoHelper;

/**
 * 主路径
 * 
 * @author srnpr 路径获取类
 */
public class TopDir extends TopBase {

	/**
	 * 获取zapdir的路径 默认是在user主目录
	 * 
	 * @return
	 */
	private String upZapDir() {
		if (StringUtils.isEmpty(TopConst.CONST_TOP_ZAPDIR)) {
			
			//TopConst.CONST_TOP_ZAPDIR = sZapDir + "/" + "zapzoos/zapdir/";
			
			String sZapDir=upServerletPath("");
			if(StringUtils.isEmpty(sZapDir))
			{
				 sZapDir = System.getProperty("user.home");
			}
			TopConst.CONST_TOP_ZAPDIR=sZapDir;
		}

		return TopConst.CONST_TOP_ZAPDIR;
	}

	/**
	 * 获取当前zapsrnpr文件夹路径 默认为upZapDir()+/zapsrnpr/zapzoos/zapdir/default
	 * 
	 * @param sPath
	 * @return
	 */
	private String upCurrentDir(String sPath) {

		String sReturnString = "";

		if (StringUtils.isEmpty(TopConst.CONST_TOP_CURRENT)) {
			TopConst.CONST_TOP_CURRENT = upZapDir() + "/default/";

		}

		sReturnString = TopConst.CONST_TOP_CURRENT;

		if (StringUtils.isNotEmpty(sPath)) {
			sReturnString = sReturnString  + sPath+ "/";
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
	 * 获取临时文件夹路径
	 * 
	 * @param sTempDir
	 *            临时目录的子文件夹
	 * @return
	 */
	public String upTempDir(String sTempDir) {

		if (StringUtils.isEmpty(TopConst.CONST_TOP_DIR_TEMP)) {
			TopConst.CONST_TOP_DIR_TEMP = upCurrentDir("temp");
			bLogDebug(0, "init TopConst.CONST_TOP_DIR_TEMP="
					+ TopConst.CONST_TOP_DIR_TEMP);
		}

		String sReturnString = TopConst.CONST_TOP_DIR_TEMP  + sTempDir+"/";
		IoHelper.createDir(sReturnString);
		return sReturnString;
	}

	/**
	 * 获取程序路径
	 * @param sSubDir
	 * @return
	 */
	public String upServerletPath(String sSubDir) {

		String sReturnString = "";

	
		
		if (StringUtils.isNotEmpty(TopConst.CONST_TOP_DIR_SERVLET)) {
			sReturnString = TopConst.CONST_TOP_DIR_SERVLET + sSubDir+"/";
			IoHelper.createDir(sReturnString);
		}

		return sReturnString;
	}

}
