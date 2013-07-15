package com.srnpr.zapweb.webdo;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebPage;

public class PageCache extends RootCache<String, MWebPage> {

	public synchronized void refresh() {

		

		for (MDataMap mPageDataMap : DbUp.upTable("zw_page").queryByWhere()) {

			MWebPage mWebPage = new MWebPage();

			mWebPage.setPageCode(mPageDataMap.get("page_code"));
			mWebPage.setPageName(mPageDataMap.get("page_name"));
			mWebPage.setPageTemplate(mPageDataMap.get("page_template"));
			mWebPage.setPageTypeAid(mPageDataMap.get("page_type_aid"));
			mWebPage.setViewCode(mPageDataMap.get("view_code"));
			
			
			super.inElement(mWebPage.getPageCode(), mWebPage);
		
			
			
			

		}

	}

}
