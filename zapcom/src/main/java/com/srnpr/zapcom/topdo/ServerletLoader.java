package com.srnpr.zapcom.topdo;

import javax.servlet.ServletContext;

public class ServerletLoader {
	
	
	public  void init(ServletContext servletContext) {

		try {


			servletContext.log("Initializing zapsrnpr.zapcom");

			//String sTopConfigString= servletContext.getInitParameter("zapcomtopconfig");
			
			//servletContext.log(sTopConfigString);
			
			TopConst.CONST_TOP_DIR_SERVLET=servletContext.getRealPath("")+"/";
			
			servletContext.log(TopConst.CONST_TOP_DIR_SERVLET);
			
			servletContext.getContextPath();
			
			new TopInit().init();

			//InitProcess(servletContext);

		}
		catch(RuntimeException ex)
		{
			servletContext.log("Error zapsrnpr.zapcom"+ex.getMessage());
		}


	}
}
