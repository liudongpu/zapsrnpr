package com.srnpr.zapweb.helper;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 检查判断类
 * 
 * @author srnpr
 * 
 */
public class WebCheckHelper implements IBaseHelper {

	/**
	 * 字段验证
	 * 
	 * @param sRegexValue
	 * @param sValue
	 * @return
	 */
	public static int recheckInputField(String sRegexValue, String sValue) {

		int iReturn = 1;

		if (StringUtils.isNotEmpty(sRegexValue)) {

			// 如果是编号系列 则获取编号对应的正则表达式
			if (sRegexValue.startsWith("46992318")) {

				sRegexValue = WebTemp.upTempDataOne("zw_define", "define_name",
						"define_dids", sRegexValue);
			}

			// 校验如果是+号开始 则判断是否允许为空
			if (sRegexValue.startsWith("+")) {
				if (StringUtils.isEmpty(sValue)) {
					iReturn = 969905003;
				} else {
					sRegexValue = StringUtils.substringAfter(sRegexValue, "+");
				}
			} else if (iReturn == 1 && sRegexValue.startsWith("-")) {

				if (StringUtils.isEmpty(sValue)) {
					return iReturn;
				} else {
					sRegexValue = StringUtils.substringAfter(sRegexValue, "-");
				}

			}

			// 开始判断正则表达式
			if (iReturn == 1 && StringUtils.isNotEmpty(sRegexValue)) {
				if (!sValue.matches(sRegexValue)) {
					iReturn = 969905002;

				}
			}
		}

		return iReturn;

	}

	/**
	 * 检查Map字段
	 * 
	 * @param map
	 *            数据集
	 * @param sSplitKeys
	 *            map中的字段名称 逗号分隔
	 * @param sSplitRegex
	 *            正则表达式 支持46992318系列 逗号分隔
	 * @param sSplitNames
	 *            名称 支持info系列 逗号分隔
	 * @return
	 */
	public static MWebResult checkMap(MDataMap map, String sSplitKeys,
			String sSplitRegex, String sSplitNames) {
		MWebResult mResult = new MWebResult();

		String[] sFieldsStrings = sSplitKeys.split(",");
		String[] sRegexStrings = sSplitRegex.split(",");
		String[] sNameStrings = sSplitNames.split(",");
		int iMaxLength = sFieldsStrings.length;
		for (int i = 0; i < iMaxLength; i++) {

			int iCheck = 1;

			if (map.containsKey(sFieldsStrings[i])) {

				iCheck = recheckInputField(sRegexStrings[i],
						map.get(sFieldsStrings[i]));
			} else {
				iCheck = 969905030;
			}

			if (iCheck != 1) {
				if (StringUtils.isNumeric(sNameStrings[i])) {
					sNameStrings[i] = TopUp.upInfo(Long
							.parseLong(sNameStrings[i]));
				}

				mResult.inErrorMessage(iCheck, sNameStrings[i]);

				break;

			}

		}

		return mResult;
	}

}
