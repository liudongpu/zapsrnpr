package com.srnpr.zapweb.webdo;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

public class PageCache extends RootCache<String, MWebPage> {

	public synchronized void refresh() {

		for (MDataMap mPageDataMap : DbUp.upTable("zw_page").queryByWhere(
				"flag_enable", "1")) {

			MWebPage mWebPage = new MWebPage();

			mWebPage.setPageCode(mPageDataMap.get("page_code"));
			mWebPage.setPageName(mPageDataMap.get("page_name"));
			mWebPage.setPageTemplate(mPageDataMap.get("page_template"));
			mWebPage.setPageTypeAid(mPageDataMap.get("page_type_aid"));
			mWebPage.setViewCode(mPageDataMap.get("view_code"));

			// 设置操作按钮
			List<MWebOperate> listOperates = new ArrayList<MWebOperate>();
			for (MDataMap mOperateDataMap : DbUp.upTable("zw_operate")
					.queryByWhere("flag_enable", "1", "page_code",
							mWebPage.getPageCode())) {
				MWebOperate mOperate = new MWebOperate();

				mOperate.setOperateName(mOperateDataMap.get("operate_name"));
				mOperate.setOperateLink(mOperateDataMap.get("operate_link"));
				mOperate.setOperateTypeAid(mOperateDataMap
						.get("operate_type_aid"));
				mOperate.setOperateUid(mOperateDataMap.get("uid"));
				mOperate.setOperateFunc(mOperateDataMap.get("operate_func"));

				listOperates.add(mOperate);
			}

			mWebPage.setPageOperate(listOperates);

			MWebView mView = WebUp.upViewCache(mWebPage.getViewCode() + "-"
					+ mPageDataMap.get("view_type_aid"));

			mWebPage.setPageTable(mView.getTableName());
			// 设置字段
			List<MWebField> listFields = new ArrayList<MWebField>();
			for (MWebField mField : mView.getFields()) {
				listFields.add(mField.clone());
			}
			mWebPage.setPageFields(listFields);

			super.inElement(mWebPage.getPageCode(), mWebPage);

		}

	}

}
