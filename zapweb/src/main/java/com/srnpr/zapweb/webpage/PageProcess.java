package com.srnpr.zapweb.webpage;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebProcess;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;

public class PageProcess implements IWebProcess {

	public ControlPage process(String sPageCode, HttpServletRequest hRequest) {

		MWebPage mPage = WebUp.upPage(sPageCode);

		MDataMap mReqMap = new MDataMap();
		@SuppressWarnings("unchecked")
		Enumeration<String> eKey = hRequest.getParameterNames();

		while (eKey.hasMoreElements()) {
			String string = eKey.nextElement();
			mReqMap.put(string,
					StringUtils.join(hRequest.getParameterValues(string), ","));
		}

		ControlPage cPage = new ControlPage();

		cPage.setReqMap(mReqMap);
		cPage.setWebPage(mPage);

		return cPage;
	}
	

	
	
	public MWebResult func(String sPageCode,String sTypeId, HttpServletRequest hRequest)
	{
		MWebResult mResult=new MWebResult();
		
		
		return mResult;
	}
	
	
	

}
