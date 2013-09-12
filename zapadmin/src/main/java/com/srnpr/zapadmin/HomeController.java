package com.srnpr.zapadmin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srnpr.zapweb.webmethod.RootControl;

@Controller
public class HomeController extends RootControl {

	
	@RequestMapping(value = "/test/{url}")
	public String test(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		
		return "page/test";
	}
	
	
	
}
