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

	public void contextInitialized(ServletContextEvent event) {


		if (this.contextLoader == null) {
			this.contextLoader = this;
		}
		this.contextLoader.init(event.getServletContext());

	}
	
}
