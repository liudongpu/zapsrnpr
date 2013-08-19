package com.srnpr.zapadmin;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srnpr.cardcenter.webprocess.WebMethod;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapweb.webexport.ExportChart;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.PageProcess;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final PageProcess page_Process = new PageProcess();
	private static final WebMethod web_method = new WebMethod();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		model.addAttribute("b_method", web_method);
		return "home";
	}

	@RequestMapping(value = "/manage/{url}")
	public String manage(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_method", web_method);
		return "manage/" + sUrl;
	}

	@RequestMapping(value = "/page/{url}")
	public String page(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_page", page_Process.process(sUrl, request));
		model.addAttribute("b_method", web_method);
		return "page/default";
	}

	@RequestMapping(value = "/show/{url}")
	public String show(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_page", page_Process.process(sUrl, request));
		model.addAttribute("b_method", web_method);
		return "page/show";
	}

	@RequestMapping(value = "/jsonchart/{url}", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String jsonchart(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
	
		return new JsonHelper<List<List<String>>>().ObjToString(page_Process
				.process(sUrl, request).upChartData().getPageData());

	}

	@RequestMapping(value = "/func/{operateId}", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String func(@PathVariable("operateId") String sOperateId,
			Model model, HttpServletRequest request) {

		return new JsonHelper<MWebResult>().ObjToString(page_Process.func(
				sOperateId, request));

	}

	@RequestMapping(value = "/export/{operateId}", produces = { "application/binary;charset=UTF-8" })
	@ResponseBody
	public String export(@PathVariable("operateId") String sOperateId,
			HttpServletRequest request, HttpServletResponse response) {

		new ExportChart().export(sOperateId, request, response);

		return null;

	}

}
