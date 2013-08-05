package com.srnpr.zapcom.basehelper;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

public class MapHelper implements IBaseHelper {

	/**
	 * 根据Key获取子集
	 * @param mDataMap
	 * @param sStartString
	 * @return
	 */
	public static MDataMap subMap(MDataMap mDataMap, String sStartString) {
		MDataMap mReturn = new MDataMap();

		for (String sKey : mDataMap.upKeys()) {
			if (StringUtils.startsWith(sKey, sStartString)) {
				mReturn.put(StringUtils.substringAfter(sKey, sStartString),
						mDataMap.get(sKey));
			}
		}

		return mReturn;
	}

}
