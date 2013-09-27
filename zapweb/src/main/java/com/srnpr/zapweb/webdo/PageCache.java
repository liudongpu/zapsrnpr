package com.srnpr.zapweb.webdo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebView;

public class PageCache extends RootCache<String, MWebPage> {

	public synchronized void refresh() {

		/*
		 * for (MDataMap mPageDataMap : DbUp.upTable("zw_page").queryByWhere(
		 * "flag_enable", "1")) {
		 * 
		 * try {
		 * 
		 * MWebPage mWebPage = new MWebPage();
		 * 
		 * mWebPage.setPageCode(mPageDataMap.get("page_code"));
		 * mWebPage.setPageName(mPageDataMap.get("page_name"));
		 * mWebPage.setPageTemplate(mPageDataMap.get("page_template"));
		 * mWebPage.setPageTypeAid(mPageDataMap.get("page_type_aid"));
		 * mWebPage.setViewCode(mPageDataMap.get("view_code"));
		 * mWebPage.setDataScope(mPageDataMap.get("data_scope")); //
		 * mWebPage.setDataSort(mPageDataMap.get("data_sort"));
		 * 
		 * // 设置操作按钮 List<MWebOperate> listOperates = new
		 * ArrayList<MWebOperate>(); for (MDataMap mOperateDataMap :
		 * DbUp.upTable("zw_operate") .queryByWhere("flag_enable", "1",
		 * "page_code", mWebPage.getPageCode())) {
		 * 
		 * MWebOperate mOperate = WebUp.upOperate(
		 * mOperateDataMap.get("uid")).clone();
		 * 
		 * listOperates.add(mOperate); }
		 * 
		 * mWebPage.setPageOperate(listOperates);
		 * 
		 * String sViewKey = mWebPage.getViewCode() + "-" +
		 * mPageDataMap.get("view_type_aid");
		 * 
		 * if (StringUtils.isNotEmpty(mWebPage.getViewCode())) {
		 * 
		 * MWebView mView = WebUp.upViewCache(sViewKey);
		 * 
		 * if (mView!=null) {
		 * 
		 * 
		 * mWebPage.setPageTable(mView.getTableName()); // 设置字段 List<MWebField>
		 * listFields = new ArrayList<MWebField>(); for (MWebField mField :
		 * mView.getFields()) { listFields.add(mField.clone()); }
		 * mWebPage.setPageFields(listFields); } else { bLogError(0,
		 * mWebPage.getPageCode() + ":" + sViewKey + " not exist"); } }
		 * 
		 * super.inElement(mWebPage.getPageCode(), mWebPage); } catch (Exception
		 * e) {
		 * 
		 * e.printStackTrace(); } }
		 */
	}

	@Override
	public MWebPage upOne(String k) {

		MWebPage mWebPage = null;

		MDataMap mPageDataMap = DbUp.upTable("zw_page").one("flag_enable", "1",
				"page_code", k);

		if (mPageDataMap != null) {

			try {

				mWebPage = new MWebPage();

				mWebPage.setPageCode(mPageDataMap.get("page_code"));
				mWebPage.setPageName(mPageDataMap.get("page_name"));
				mWebPage.setPageTemplate(mPageDataMap.get("page_template"));
				mWebPage.setPageTypeAid(mPageDataMap.get("page_type_aid"));
				mWebPage.setViewCode(mPageDataMap.get("view_code"));
				mWebPage.setDataScope(mPageDataMap.get("data_scope"));
				// mWebPage.setDataSort(mPageDataMap.get("data_sort"));

				// 设置操作按钮
				List<MWebOperate> listOperates = new ArrayList<MWebOperate>();
				for (MDataMap mOperateDataMap : DbUp.upTable("zw_operate")
						.queryByWhere("flag_enable", "1", "page_code",
								mWebPage.getPageCode())) {

					MWebOperate mOperate = WebUp.upOperate(
							mOperateDataMap.get("uid")).clone();

					listOperates.add(mOperate);
				}

				mWebPage.setPageOperate(listOperates);

				String sViewKey = mWebPage.getViewCode() + "-"
						+ mPageDataMap.get("view_type_aid");

				if (StringUtils.isNotEmpty(mWebPage.getViewCode())) {

					MWebView mView = WebUp.upViewCache(sViewKey);

					if (mView != null) {

						mWebPage.setPageTable(mView.getTableName());
						// 设置字段
						List<MWebField> listFields = new ArrayList<MWebField>();
						for (MWebField mField : mView.getFields()) {
							listFields.add(mField.clone());
						}
						mWebPage.setPageFields(listFields);
					} else {
						bLogError(0, mWebPage.getPageCode() + ":" + sViewKey
								+ " not exist");
					}
				}

				super.inElement(mWebPage.getPageCode(), mWebPage);
			} catch (Exception e) {
				bLogError(969905001, k);
				e.printStackTrace();
			}
		} else {
			bLogError(969905001, k);
		}

		return mWebPage;
	}

}
