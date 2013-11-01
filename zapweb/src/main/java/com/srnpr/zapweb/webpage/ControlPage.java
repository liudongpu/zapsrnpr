package com.srnpr.zapweb.webpage;


import java.util.List;



import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basemodel.MKvdList;
import com.srnpr.zapcom.basemodel.MKvdModel;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IControlPage;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebSource;

public class ControlPage extends RootPage implements IControlPage {

	/**
	 * 页面操作类
	 */
	private final static PageExec pageExec = new PageExec();

	/**
	 * 得到列表页数据
	 * 
	 * @return
	 */
	public MPageData upChartData() {
		return pageExec.chartData(getWebPage(), getReqMap());
	}
	
	
	
	
	

	/**
	 * 得到添加页数据
	 * 
	 * @return
	 */
	public List<MWebField> upAddData() {
		return pageExec.addData(getWebPage(), getReqMap());
	}

	/**
	 * 得到修改页数据
	 * 
	 * @return
	 */
	public List<MWebField> upEditData() {
		return pageExec.editData(getWebPage(), getReqMap());
	}

	/**
	 * 得到修改页数据
	 * 
	 * @return
	 */
	public List<MWebField> upBookData() {
		return pageExec.bookData(getWebPage(), getReqMap());
	}

	/**
	 * 查询区域
	 * 
	 * @return
	 */
	public List<MWebField> upInquireData() {
		return pageExec.inquireData(getWebPage(), getReqMap());
	}

	public String upReplaceUrl(String sUrl, String... sReplace) {

		if (StringUtils.isEmpty(sUrl)) {
			sUrl = getPageUrl();
		}

		String[] sSplitUrl = StringUtils.split(sUrl, "?");

		MDataMap mQueryMap = new MDataMap();
		if (sSplitUrl.length > 1) {

			for (String s : sSplitUrl[1].split("&")) {
				mQueryMap.put(StringUtils.substringBefore(s, "="), s);
			}
		}

		for (String s : sReplace) {
			mQueryMap.put(StringUtils.substringBefore(s, "="), s);
		}

		sUrl = sSplitUrl[0] + "?" + StringUtils.join(mQueryMap.values(), "&");

		return sUrl;
	}

	/**
	 * 获取数据源
	 * 
	 * @param mField
	 * @return
	 */
	public List<MKvdModel> upDataSource(MWebField mField) {

		MKvdList mReturnList = new MKvdList();

		MWebSource mSource = WebUp.upSource(mField.getSourceCode());

		MDataMap mWhereMap = new MDataMap();
		String sWhereString = "";

		if (StringUtils.isNotEmpty(mSource.getWhereEdit())) {
			sWhereString = FormatHelper.formatString(mSource.getWhereEdit(),
					mField.getSourceParam());
		}

		for (MDataMap mDataMap : DbUp.upTable(mSource.getSourceFrom())
				.queryAll(
						mSource.getFieldText() + " as fieldText, "
								+ mSource.getFieldValue() + " as fieldValue",
						mSource.getFieldSort(), sWhereString, mWhereMap)) {

			mReturnList.inElement(mDataMap.get("fieldText"),
					mDataMap.get("fieldValue"));

		}

		return mReturnList.getChildList();
	}

	public String upConst(String sType, String... sParams) {
		String sReturn = "";
		if (sType.equals("126022006")) {
			sReturn = WebConst.CONST_WEB_FIELD_NAME + sParams[0];
		} else if (sType.equals("126022001")) {
			sReturn = sParams[0] + WebConst.CONST_WEB_FIELD_AFTER + sParams[1];
		} else if (sType.equals("126022016")) {
			sReturn = WebConst.CONST_WEB_PAGINATION_NAME + sParams[0];
		} else if (sType.equals("126022005")) {
			sReturn = WebConst.CONST_WEB_FIELD_EXTEND + sParams[0];
		}

		return sReturn;
	}

	/**
	 * 获取配置
	 * 
	 * @param sKey
	 * @param sParams
	 * @return
	 */
	public String upConfig(String sKey, Object... sParams) {
		return FormatHelper.formatString(TopUp.upConfig(sKey), sParams);

	}

}
