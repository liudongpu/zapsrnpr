package com.srnpr.zapcom.basemodel;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;

public class MDataMap extends MObjMap<String, String> implements
		Map<String, String> {
	/**
	 * 转换主键到数组
	 * 
	 * @return
	 */
	public String[] convertKeysToStrings() {
		return this.upKeys().toArray(new String[] {});
	}

	/**
	 * 获取子数据 根据传入参数取得以该参数开头的集合
	 * 
	 * @param sStartString
	 * @return
	 */
	public MDataMap upSubMap(String sStartString) {
		MDataMap mReturn = new MDataMap();

		for (String sKey : this.upKeys()) {
			if (StringUtils.startsWith(sKey, sStartString)) {
				mReturn.put(StringUtils.substringAfter(sKey, sStartString),
						this.get(sKey));
			}
		}

		return mReturn;
	}

	/**添加URL参数  URL结构体为a=b&c=d
	 * @param sParams
	 * @return
	 */
	public MDataMap inUrlParams(String sParams) {
		this.inAllValues(FormatHelper.upUrlStrings(sParams));
		return this;
	}

	public String[] upStrings() {
		ArrayList<String> aList = new ArrayList<String>();
		for (String sKey : this.upKeys()) {
			aList.add(sKey);
			aList.add(this.get(sKey));
		}

		return aList.toArray(new String[] {});

	}

	public MDataMap() {

	}

	public MDataMap(String... sInputs) {
		inAllValues(sInputs);
	}

	public MDataMap(Map<String, Object> mInput) {

		for (String s : mInput.keySet()) {

			if (mInput.get(s) != null) {

				put(s, mInput.get(s).toString());
			} else {
				put(s, "");
			}
		}
	}

}
