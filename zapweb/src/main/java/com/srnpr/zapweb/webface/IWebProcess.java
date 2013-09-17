package com.srnpr.zapweb.webface;

import javax.servlet.http.HttpServletRequest;

import com.srnpr.zapweb.webpage.ControlPage;

public interface IWebProcess {

	
	public IControlPage process(String sPageCode,HttpServletRequest hRequest);
	
	
}
