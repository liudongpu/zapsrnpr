package com.srnpr.zapweb.webpage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebSource;
import com.srnpr.zapweb.webmodel.MWebView;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

public class RootExec extends BaseClass {

	/**
	 * 获取页面数据方法 该方法为统一获取数据方法 根据不同应用场景传入不同设置参数
	 * 
	 * @param webPage
	 *            页面
	 * @param mReqMap
	 *            输入参数
	 * @param mOptionMap
	 *            设置参数 操作类型： optionExport 如果为1则标记为导出
	 * @return
	 */
	public MPageData upChartData(MWebPage webPage, MDataMap mReqMap,
			MDataMap mOptionMap) {

		// 返回参数
		MPageData mReturnData = new MPageData();

		/********** 开始处理分页输入参数逻辑 ********************************/
		{
			MDataMap mPaginationMap = mReqMap
					.upSubMap(WebConst.CONST_WEB_PAGINATION_NAME);

			if (mPaginationMap.containsKey("count")) {
				mReturnData.setPageCount(Integer.valueOf(mPaginationMap
						.get("count")));
			}
			if (mPaginationMap.containsKey("index")) {
				mReturnData.setPageIndex(Integer.valueOf(mPaginationMap
						.get("index")));
			}
		}

		// 数据
		List<List<String>> listData = new ArrayList<List<String>>();

		// 获取字段
		List<MWebField> listFields = recheckFields(webPage.getPageFields(),
				mReqMap);

		List<MWebOperate> listOperates = null;
		// 定义列表中显示的按钮列
		String sOperateArea = "116001003";

		/********** 开始根据输入参数重置各种设置 ********************************/
		{

			// 判断如果是导出操作 则设置各种数据条件
			if (mOptionMap.containsKey("optionExport")
					&& mOptionMap.get("optionExport").equals("1")) {
				sOperateArea = "";
				mReturnData.setPageSize(-1);
				mReturnData.setPageCount(1);

			}
		}

		// 判断是否初始化列表页的附加按钮列
		if (StringUtils.isNotEmpty(sOperateArea)) {
			listOperates = recheckOperates(webPage.getPageOperate(),
					sOperateArea);
		}

		/********** 开始处理表头 ********************************/
		{

			List<String> listHeader = new ArrayList<String>();
			for (MWebField mField : listFields) {
				listHeader.add(mField.getFieldNote());
			}

			if (StringUtils.isNotEmpty(sOperateArea)) {

				for (MWebOperate mWebOperate : listOperates) {
					listHeader.add(mWebOperate.getOperateName());
				}
			}

			mReturnData.setPageHead(listHeader);
		}

		MDataMap mQueryMap = new MDataMap();
		String sWhere = "";

		/********** 开始处理查询输入 ********************************/
		{

			// 开始加载查询条件判断
			if (mReqMap.size() > 0) {

				ArrayList<String> aWhereStrings = new ArrayList<String>();

				for (MWebField mField : upInquireData(webPage, mReqMap)) {

					switch (Integer.parseInt(mField.getQueryTypeAid())) {

					// 如果是范围查询
					case 104009002:

						if (StringUtils.isNotEmpty(mReqMap.get(mField
								.getPageFieldName()
								+ WebConst.CONST_WEB_FIELD_AFTER
								+ "between_from"))) {

							aWhereStrings.add(mField.getColumnName() + ">=:"
									+ mField.getColumnName()
									+ WebConst.CONST_WEB_FIELD_AFTER
									+ "between_from");
							mQueryMap.put(
									mField.getColumnName()
											+ WebConst.CONST_WEB_FIELD_AFTER
											+ "between_from",
									mReqMap.get(mField.getPageFieldName()
											+ WebConst.CONST_WEB_FIELD_AFTER
											+ "between_from"));

						}

						if (StringUtils
								.isNotEmpty(mReqMap.get(mField
										.getPageFieldName()
										+ WebConst.CONST_WEB_FIELD_AFTER
										+ "between_to"))) {

							aWhereStrings.add(mField.getColumnName() + "<=:"
									+ mField.getColumnName()
									+ WebConst.CONST_WEB_FIELD_AFTER
									+ "between_to");
							mQueryMap.put(
									mField.getColumnName()
											+ WebConst.CONST_WEB_FIELD_AFTER
											+ "between_to",
									mReqMap.get(mField.getPageFieldName()
											+ WebConst.CONST_WEB_FIELD_AFTER
											+ "between_to"));

						}

						break;

					// 如果是like查询
					case 104009012:

						if (StringUtils.isNotEmpty(mField.getPageFieldValue())) {
							aWhereStrings.add(" " + mField.getColumnName()
									+ " like :" + mField.getColumnName());
							mQueryMap.put(mField.getColumnName(),
									"%" + mField.getPageFieldValue() + "%");
						}
						break;

					// 默认走等于
					default:

						if (StringUtils.isNotEmpty(mField.getPageFieldValue())) {
							aWhereStrings.add(" " + mField.getColumnName()
									+ " = :" + mField.getColumnName());
							mQueryMap.put(mField.getColumnName(),
									mField.getPageFieldValue());
						}

						break;
					}

				}

				if (aWhereStrings.size() > 0) {
					sWhere = StringUtils.join(aWhereStrings, " and ");
				}

			}
		}

		/********** 开始统计数据 ********************************/
		{

			// 判断如果没有请求则重新统计数量
			if (mReturnData.getPageCount() < 0) {
				mReturnData.setPageCount(DbUp.upTable(webPage.getPageTable())
						.dataCount(sWhere, mQueryMap));

			}

			// 判断最大数量是否有
			if (mReturnData.getPageMax() < 0) {
				mReturnData.setPageMax((int) Math.ceil((double) mReturnData
						.getPageCount() / (double) mReturnData.getPageSize()));
			}
		}

		/********** 开始处理数据 ********************************/
		{

			// 开始加载数据
			for (MDataMap mData : DbUp.upTable(webPage.getPageTable()).query(
					WebHelper.upFieldSql(webPage.getPageFields()),
					"-zid",
					sWhere,
					mQueryMap,
					(mReturnData.getPageIndex() - 1)
							* mReturnData.getPageSize(),
					mReturnData.getPageSize())) {
				List<String> listEach = new ArrayList<String>();

				for (MWebField mField : listFields) {

					// 判断如果是组件则重新输出文字
					if (mField.getFieldTypeAid().equals("104005003")) {
						listEach.add(WebUp.upComponent(mField.getSourceCode())
								.upListText(mField, mData));
					} else {
						listEach.add(mData.get(mField.getColumnName()));
					}

				}

				// 判断是否是加载附加按钮列
				if (StringUtils.isNotEmpty(sOperateArea)) {
					for (MWebOperate mWebOperate : listOperates) {
						listEach.add(reloadOperateText(mWebOperate, mData));
					}
				}

				listData.add(listEach);

			}
		}

		/********** 根据规则重新处理数据 ********************************/
		{
			// 重新加载输出字段 判断加载替换显示等操作
			for (int i = 0, j = listFields.size(); i < j; i++) {
				if (listFields.get(i).getFieldTypeAid().equals("104005019")) {
					MWebSource mSource = WebUp.upSource(listFields.get(i)
							.getSourceCode());

					List<String> listSqlSub = new ArrayList<String>();

					for (List<String> listSub : listData) {
						listSqlSub.add("" + listSub.get(i) + "");
					}

					List<MDataMap> listResultDataMaps = DbUp.upTable(
							mSource.getSourceFrom()).queryAll(
							mSource.getFieldText() + " as field_text,"
									+ mSource.getFieldValue()
									+ " as field_value ",
							"",
							" instr(:field_list,concat("
									+ mSource.getFieldValue() + ",','))>0  ",
							new MDataMap("field_list", StringUtils.join(
									listSqlSub, ",")));

					MDataMap mKeyMap = new MDataMap();
					for (MDataMap mMap : listResultDataMaps) {
						mKeyMap.put(mMap.get("field_value"),
								mMap.get("field_text"));
					}

					for (int n = 0, m = listData.size(); n < m; n++) {

						String sNowString = listData.get(n).get(i);
						if (mKeyMap.containsKey(sNowString)) {
							sNowString = mKeyMap.get(sNowString);
						} else {
							sNowString = "";
						}

						listData.get(n).set(i, sNowString);
					}

				}
			}
		}

		mReturnData.setPageData(listData);
		return mReturnData;

	}

	/**
	 * 重新加载按钮配置输出字段
	 * 
	 * @param mWebOperate
	 * @param mDataMap
	 * @return
	 */
	public String reloadOperateText(MWebOperate mWebOperate, MDataMap mDataMap) {
		Pattern p = Pattern.compile("\\[@(.+?)\\$(.*?)\\]");

		String sReturn = mWebOperate.getOperateLink();
		Matcher m = p.matcher(sReturn);

		while (m.find()) {

			String sFull = m.group(0);
			String sKey = m.group(1);
			String sAttr = m.group(2);

			String sReplace = "";

			if (sKey.equals("this")) {
				if (mDataMap.containsKey(sAttr)) {
					sReplace = mDataMap.get(sAttr);
				}
			}

			sReturn = sReturn.replace(sFull, sReplace);

		}

		// 如果是链接 则输出链接
		if (mWebOperate.getOperateTypeAid().equals("116015012")) {

			sReturn = WebHelper.checkUrl(FormatHelper.formatString(
					bConfig("zapweb.html_linkblank"), sReturn,
					mWebOperate.getOperateName()));

		}

		return sReturn;
	}

	public List<MWebOperate> recheckOperates(List<MWebOperate> listOperates,
			String sOperateArea) {
		List<MWebOperate> listReturns = new ArrayList<MWebOperate>();

		for (MWebOperate mWebOperate : listOperates) {
			if (mWebOperate.getAreaTypeAid().equals(sOperateArea)) {
				listReturns.add(mWebOperate);
			}
		}

		return listReturns;
	}

	/**
	 * 重新审查字段
	 * 
	 * @param inputFields
	 * @return
	 */
	public List<MWebField> recheckFields(List<MWebField> inputFields,
			MDataMap mReqMap) {
		List<MWebField> listReturnFields = new ArrayList<MWebField>();

		for (MWebField mField : inputFields) {
			if (!mField.getSort().equals("0")) {

				MWebField mCloneField = mField.clone();

				if (mReqMap.containsKey(mCloneField.getPageFieldName())) {
					mCloneField.setPageFieldValue(mReqMap.get(mCloneField
							.getPageFieldName()));
				}

				listReturnFields.add(mCloneField);

			}
		}

		return listReturnFields;
	}

	/**
	 * 获取查询数据
	 * 
	 * @param webPage
	 * @param mReqMap
	 * @return
	 */
	public List<MWebField> upInquireData(MWebPage webPage, MDataMap mReqMap) {

		MWebView mView = WebUp.upQueryView(webPage.getViewCode());
		List<MWebField> listFields = recheckFields(mView.getFields(), mReqMap);

		return listFields;

	}

}
