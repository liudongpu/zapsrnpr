package com.srnpr.zapcom.topdo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化 加载各种配置和初始化类
 * @author srnpr
 *
 */
public class ServerletListener extends ServerletLoader implements ServletContextListener{

	
	
	private ServerletLoader contextLoader;

	public void contextDestroyed(ServletContextEvent event) {


	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {


		if (this.contextLoader == null) {
			this.contextLoader = this;
		}
		this.contextLoader.init(event.getServletContext());

	}
	
}
