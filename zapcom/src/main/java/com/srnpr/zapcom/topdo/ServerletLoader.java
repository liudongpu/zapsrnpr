package com.srnpr.zapcom.topdo;

import javax.servlet.ServletContext;

public class ServerletLoader {
	
	
	public  void Init(ServletContext servletContext) {

		try {


			servletContext.log("Initializing zapsrnpr.zapcom");

			
			new TopInit().init();

			//InitProcess(servletContext);

		}
		catch(RuntimeException ex)
		{
			servletContext.log("Error zapsrnpr.zapcom"+ex.getMessage());
		}


	}
}
