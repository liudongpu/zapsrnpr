package com.srnpr.zapcom.baseinit;

import javax.servlet.ServletContext;

public class ServerletLoader {
	
	
	public  void Init(ServletContext servletContext) {

		try {


			servletContext.log("Initializing srnprjava");


			//InitProcess(servletContext);

		}
		catch(RuntimeException ex)
		{
			servletContext.log("Initializing zsrnpr Error"+ex.getMessage());
		}


	}
}
