package com.srnpr.zapcom.topdo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerletListener extends ServerletLoader implements ServletContextListener{

	
	
	private ServerletLoader contextLoader;

	public void contextDestroyed(ServletContextEvent event) {


	}

	public void contextInitialized(ServletContextEvent event) {


		if (this.contextLoader == null) {
			this.contextLoader = this;
		}
		this.contextLoader.Init(event.getServletContext());

	}
	
}
