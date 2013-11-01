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
	 * 获取临时文件夹路径
	 * 
	 * @param sTempDir
	 *            临时目录的子文件夹
	 * @return
	 */
	public String upTempDir(String sTempDir) {

		if (StringUtils.isEmpty(TopConst.CONST_TOP_DIR_TEMP)) {
			TopConst.CONST_TOP_DIR_TEMP = upServerletPath("zapzoos/zapdir/temp/");
			bLogDebug(0, "init TopConst.CONST_TOP_DIR_TEMP="
					+ TopConst.CONST_TOP_DIR_TEMP);
		}
		String sReturnString = TopConst.CONST_TOP_DIR_TEMP + sTempDir
				+ (StringUtils.isBlank(sTempDir) ? "" : "/");
		IoHelper.createDir(sReturnString);
		return sReturnString;
	}

	/**
	 * 获取程序路径
	 * 
	 * @param sSubDir
	 * @return
	 */
	public String upServerletPath(String sSubDir) {

		String sReturnString = "";

		if (StringUtils.isBlank(TopConst.CONST_TOP_DIR_SERVLET)) {
			TopConst.CONST_TOP_DIR_SERVLET = System.getProperty("user.home");
		}

		if (StringUtils.isNotBlank(sSubDir)) {
			sSubDir = "/" + sSubDir;
		}

		sReturnString = TopConst.CONST_TOP_DIR_SERVLET + sSubDir;
		IoHelper.createDir(sReturnString);
		return sReturnString;

	}

	/**
	 * 获取加载扩展配置目录
	 * 
	 * @return
	 */
	public String upCustomPath(String sPath) {
		String sReturn = "";

		if (StringUtils.isBlank(TopConst.CONST_TOP_DIR_CUSTOM)) {

			String sServerPath = upServerletPath("");

			String sStart = "/etc/zapsrnpr/";

			
			
			// 判断如果是windows系统 则默认取系统所在路径的根目录
			if (StringUtils.substring(sServerPath, 1, 2).equals(":")) {
				sStart = sServerPath.substring(0, 2) + sStart;
			}

			sServerPath = sServerPath.toLowerCase().replace(":", "_")
					.replace("/", "_").replace("\\", "_");

			sStart = sStart + sServerPath;

			TopConst.CONST_TOP_DIR_CUSTOM = sStart + "/";

		}

		sReturn = TopConst.CONST_TOP_DIR_CUSTOM + sPath;
		IoHelper.createDir(sReturn);
		return sReturn;

	}

}
