package com.srnpr.zapweb.webfunc;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebOperate;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebView;

/**
 * 添加
 * 
 * @author srnpr
 * 
 */
public class FuncAdd extends RootFunc {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapweb.webface.IWebFunc#funcDo(java.lang.String,
	 * com.srnpr.zapcom.basemodel.MDataMap)
	 */
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MWebOperate mOperate = WebUp.upOperate(sOperateUid);

		MWebPage mPage = WebUp.upPage(mOperate.getPageCode());

		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		// 定义插入数据库
		MDataMap mInsertMap = new MDataMap();
		// 定义组件判断标记
		boolean bFlagComponent = false;

		recheckMapField(mResult, mPage, mAddMaps);

		if (mResult.upFlagTrue()) {

			// 循环所有结构 初始化插入map
			for (MWebField mField : mPage.getPageFields()) {

				if (mField.getFieldTypeAid().equals("104005003")) {
					bFlagComponent = true;
				}

				if (mAddMaps.containsKey(mField.getFieldName())
						&& StringUtils.isNotEmpty(mField.getColumnName())) {

					String sValue = mAddMaps.get(mField.getFieldName());

					mInsertMap.put(mField.getColumnName(), sValue);
				}

				// 如果设置不为空 则进行各种校验
				if (StringUtils.isNotEmpty(mField.getFieldScope())) {

					MDataMap mFieldScope = new MDataMap().inUrlParams(mField
							.getFieldScope());

					MDataMap mScopeMap = mFieldScope
							.upSubMap(WebConst.CONST_WEB_PAGINATION_NAME);

					String sDefaultValue = "";

					if (mScopeMap.containsKey("defaultvalue")) {
						sDefaultValue = mScopeMap.get("defaultvalue");
					}

					// 判断默认值
					if (StringUtils.isNotEmpty(sDefaultValue)) {
						String sValue = "";

						if (StringUtils.contains(sDefaultValue,
								WebConst.CONST_WEB_SET_REPLACE)) {

							//重新格式化参数
							sValue = WebHelper.recheckReplace(sDefaultValue,
									mDataMap);

						} else {
							sValue = sDefaultValue;
						}

						if (StringUtils.isNotEmpty(sValue)) {
							mDataMap.put(
									WebConst.CONST_WEB_FIELD_NAME
											+ mField.getFieldName(), sValue);

							mInsertMap.put(mField.getColumnName(), sValue);
						}

					}

					// 如果有附件设置
					if (mScopeMap.containsKey("targetset")) {
						String sTargetSetString = mScopeMap.get("targetset");

						// 校验字段的唯一
						if (sTargetSetString.equals("unique")) {

							if (DbUp.upTable(mPage.getPageTable()).count(
									mField.getColumnName(),
									mInsertMap.get(mField.getColumnName())) > 0) {

								mResult.inErrorMessage(969905004,
										mField.getFieldNote());
							}

						}
						// 自动生成code
						else if (sTargetSetString.equals("code")) {

							MDataMap mSetMap = mFieldScope
									.upSubMap(WebConst.CONST_WEB_FIELD_SET);

							String sCodeName = mSetMap.get("codename");

							String sParentValue = mAddMaps.get(sCodeName);

							MDataMap mTopDataMap = DbUp.upTable(
									mPage.getPageTable()).oneWhere(
									mField.getColumnName(),
									"-"+mField.getColumnName(), "", sCodeName,
									sParentValue);

							if (mTopDataMap != null) {

								String sMaxString = mTopDataMap.get(mField
										.getColumnName());
								long lMax = Long.parseLong(StringUtils.right(sMaxString, 4)) + 1;

								
								
								mInsertMap.put(mField.getColumnName(),
										sParentValue+ StringUtils.leftPad(String.valueOf(lMax), 4, "0"));

							} else {
								String sMaxAdd = sParentValue
										+ (mSetMap.containsKey("maxadd") ? mSetMap
												.get("maxadd") : "0001");

								mInsertMap.put(mField.getColumnName(),
										String.valueOf(sMaxAdd));

							}

						}

					}

				}

			}
		}

		if (mResult.upFlagTrue()) {
			DbUp.upTable(mPage.getPageTable()).dataInsert(mInsertMap);

			if (bFlagComponent) {

				for (MWebField mField : mPage.getPageFields()) {
					if (mField.getFieldTypeAid().equals("104005003")) {

						WebUp.upComponent(mField.getSourceCode()).inAdd(mField,
								mDataMap);

					}
				}

			}

		}

		if (mResult.upFlagTrue()) {
			mResult.setResultMessage(bInfo(969909001));
		}
		return mResult;

	}
}
