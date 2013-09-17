package com.srnpr.zapweb.webpage;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IControlPage;
import com.srnpr.zapweb.webface.IWebProcess;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 页面处理 该类为页面处理类 返回控制页面操作
 * 
 * @author srnpr
 * 
 */
public class RootProcess implements IWebProcess {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebProcess#process(java.lang.String,
	 * javax.servlet.http.HttpServletRequest)
	 */
	public IControlPage process(String sPageCode, HttpServletRequest hRequest) {

		try {
			hRequest.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		MWebPage mPage = WebUp.upPage(sPageCode);

		IControlPage cPage = new ControlPage();

		// 设置连接
		if (StringUtils.isEmpty(hRequest.getQueryString())) {
			cPage.setPageUrl(hRequest.getRequestURL().toString());
		} else {
			cPage.setPageUrl(hRequest.getRequestURL().toString() + "?"
					+ hRequest.getQueryString());
		}

		MDataMap mReqMap = convertRequest(hRequest);
		mReqMap.inAllValues(FormatHelper.upUrlStrings(mPage.getDataScope()));

		cPage.setReqMap(mReqMap);
		cPage.setWebPage(mPage);

		return cPage;
	}

	/**
	 * 转换reques的值
	 * 
	 * @param hRequest
	 * @return
	 */
	public MDataMap convertRequest(HttpServletRequest hRequest) {
		MDataMap mReqMap = new MDataMap();
		@SuppressWarnings("unchecked")
		Enumeration<String> eKey = hRequest.getParameterNames();

		while (eKey.hasMoreElements()) {
			String string = eKey.nextElement();
			mReqMap.put(string,
					StringUtils.join(hRequest.getParameterValues(string), ","));
		}

		return mReqMap;
	}

	/**
	 * 操作调用
	 * 
	 * @param sPageCode
	 * @param sTypeId
	 * @param hRequest
	 * @return
	 */
	public MWebResult func(String sTypeId, HttpServletRequest hRequest) {

		String sFuncName = WebUp.upOperate(sTypeId).getOperateFunc();

		return WebUp.upFunc(sFuncName)
				.funcDo(sTypeId, convertRequest(hRequest));
	}

}
