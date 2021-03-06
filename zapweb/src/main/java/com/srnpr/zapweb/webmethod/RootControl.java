package com.srnpr.zapweb.webmethod;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webexport.ExportChart;
import com.srnpr.zapweb.webfactory.ApiFactory;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webpage.PageProcess;

/**
 * 基本控制类
 * 
 * @author srnpr
 * 
 */
public class RootControl {
	private static final PageProcess page_Process = new PageProcess();
	private static final WebMethod web_method = new WebMethod();

	/**
	 * 首页
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		model.addAttribute("b_method", web_method);
		return "home";
	}

	/**
	 * 后台
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manage/{url}")
	public String manage(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_method", web_method);
		return "manage/" + sUrl;
	}
	
	
	/**
	 * 后台
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/system/{url}")
	public String system(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_method", web_method);
		return "system/" + sUrl;
	}
	
	

	/**
	 * 页面
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/page/{url}")
	public String page(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_page", page_Process.process(sUrl, request));
		model.addAttribute("b_method", web_method);
		return web_method.checkLogin("page/default");
	}

	/**
	 * 展示，该操作通常用于展示list
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/show/{url}")
	public String show(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_page", page_Process.process(sUrl, request));
		model.addAttribute("b_method", web_method);
		return web_method.checkLogin("page/show");
	}

	/**
	 * 上传文件
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upload/{url}")
	public String upload(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		model.addAttribute("b_html",
				WebUpload.create().uploadFile(request, sUrl));
		String sReturnPageString = "ready";
		if (sUrl.equals(WebConst.CONST_STATIC_WEB_UPLOAD_SAVE)) {
			sReturnPageString = "empty";
		}

		return "page/" + sReturnPageString;

	}

	/**
	 * json数据
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jsonchart/{url}", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String jsonchart(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		
		  return web_method.checkLogin(new JsonHelper<List<List<String>>>()
		  .ObjToString(page_Process.process(sUrl, request).upChartData()
		  .getPageData()));
		 

		//return new JsonHelper<List<List<String>>>().ObjToString(page_Process
				//.process(sUrl, request).upChartData().getPageData());
	}

	/**
	 * json数据
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jsonapi/{url}", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String jsonapi(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		return ApiFactory.INSTANCE.upProcess(sUrl,request);

	}
	
	
	
	/**
	 * 异步调用json格式返回
	 * 
	 * @param sUrl
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jsonpajax/{url}", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String jsonp(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		String sReturnString= ApiFactory.INSTANCE.upProcess(sUrl,request);
		
		String sCallBackString=request.getParameter("api_callback");
		
		return sCallBackString+"("+sReturnString+")";
		
		

	}
	
	

	/**
	 * 函数操作
	 * 
	 * @param sOperateId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/func/{operateId}", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String func(@PathVariable("operateId") String sOperateId,
			Model model, HttpServletRequest request) {

		return new JsonHelper<MWebResult>().ObjToString(page_Process.func(
				sOperateId, request));

	}

	/**
	 * 导出
	 * 
	 * @param sOperateId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/export/{operateId}", produces = { "application/binary;charset=UTF-8" })
	@ResponseBody
	public String export(@PathVariable("operateId") String sOperateId,
			HttpServletRequest request, HttpServletResponse response) {

		new ExportChart().export(sOperateId, request, response);

		return null;

	}

}
