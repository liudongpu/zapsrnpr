package com.srnpr.zapadmin;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.srnpr.zapweb.webpage.PageProcess;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private static final PageProcess page_Process = new PageProcess();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/page/{url}")
	public String page(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		//model.addAttribute("serverTime", "");

		model.addAttribute("b_page", page_Process.process(sUrl, request));

		return "page/default";
	}
	
	
	@RequestMapping(value = "/func/{type}/{url}")
	public String func(@PathVariable("type") String sType,@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		//model.addAttribute("serverTime", "");

		model.addAttribute("b_page", page_Process.process(sUrl, request));

		return "page/default";
	}
	
	
	
	
	

}
