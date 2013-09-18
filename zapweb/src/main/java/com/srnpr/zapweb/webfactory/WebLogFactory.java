package com.srnpr.zapweb.webfactory;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapdata.dbdo.DbUp;

/**
 * 日志
 * @author srnpr
 *
 */
public class WebLogFactory implements IBaseInstance {

	public final static WebLogFactory INSTANCE = new WebLogFactory();

	/**
	 * 添加日志内容
	 * 
	 * @param sType
	 * @param sTitle
	 * @param sContent
	 */
	public void addLog(String sType, String sTitle, String sContent) {
		DbUp.upTable("za_weblog").insert("log_type", sType, "log_title",
				sTitle, "log_content", sContent, "create_time",
				FormatHelper.upDateTime());
	}

}
