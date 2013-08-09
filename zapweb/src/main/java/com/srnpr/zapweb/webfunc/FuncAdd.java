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

		MDataMap mAddMaps = upFieldMap(mDataMap);

		// 定义插入数据库
		MDataMap mInsertMap = new MDataMap();
		// 定义组件判断标记
		boolean bFlagComponent = false;

		recheckMapField(mResult, sOperateUid, mAddMaps);

		if (mResult.upFlagTrue()) {

			// 循环所有结构
			for (MWebField mField : mPage.getPageFields()) {

				if (mField.getFieldTypeAid().equals("104005003")) {
					bFlagComponent = true;
				}

				if (mAddMaps.containsKey(mField.getFieldName())) {

					String sValue = mAddMaps.get(mField.getFieldName());

					mInsertMap.put(mField.getColumnName(), sValue);
				}

				// 如果默认值不为空 则进行各种校验
				if (StringUtils.isNotEmpty(mField.getDefaultValue())) {

					String sDefaultValue = mField.getDefaultValue();
					if (StringUtils.isNotEmpty(sDefaultValue)) {
						String sValue = "";

						if (sDefaultValue.startsWith("@")) {
							String sKeyString = StringUtils.substringBetween(
									sDefaultValue, "@", "$");

							String sRightString = StringUtils.substringAfter(
									sDefaultValue, "$");

							// 当前时间
							if (sKeyString.equals("datenow")) {
								sValue = FormatHelper.upDateTime();
							}
							// 系统编号
							else if (sKeyString.equals("code")) {
								sValue = WebHelper.upCode(sRightString);
							} else if (sKeyString.equals("uid")) {
								// sValue = ;
							}
							// 特殊判断是否是唯一校验
							else if (sKeyString.equals("unique")) {

								int iCount = DbUp.upTable(mPage.getPageTable())
										.count(mField.getColumnName(),
												mInsertMap.get(mField
														.getFieldName()));
								if (iCount > 0) {
									mResult.inErrorMessage(969905004,
											mField.getFieldNote());
								}

							}

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
