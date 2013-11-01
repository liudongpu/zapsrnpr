package com.srnpr.zapcom.topdo;

import javax.servlet.ServletContext;

/**
 * Serverlet加载时调用
 * 
 * @author srnpr
 * 
 */
public class ServerletLoader {

	/**
	 * 初始化
	 * 
	 * @param servletContext
	 */
	public void init(ServletContext servletContext) {

		try {

			servletContext.log("Initializing zapsrnpr.zapcom");

			// String sTopConfigString=
			// servletContext.getInitParameter("zapcomtopconfig");

			// servletContext.log(sTopConfigString);

			TopConst.CONST_TOP_DIR_SERVLET = servletContext.getRealPath("");

			servletContext.log(TopConst.CONST_TOP_DIR_SERVLET);

			// servletContext.getContextPath();

			new TopInit().init();

			// InitProcess(servletContext);

		} catch (RuntimeException ex) {
			servletContext.log("Error zapsrnpr.zapcom" + ex.getMessage());
		}

	}
}
