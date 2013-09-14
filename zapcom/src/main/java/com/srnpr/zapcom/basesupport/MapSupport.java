package com.srnpr.zapcom.basesupport;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

public class MapSupport implements IBaseInstance {

	public final static MapSupport INSTANCE = new MapSupport();

	public MDataMap upSerializeMap(MDataMap mDataMap) {

		MDataMap mReturnMap = new MDataMap();

		for (String sKey : mDataMap.keySet()) {

			mReturnMap.put(FormatHelper.upReplaceSerialize(sKey),
					mDataMap.get(sKey));
		}

		return mReturnMap;

	}

}
