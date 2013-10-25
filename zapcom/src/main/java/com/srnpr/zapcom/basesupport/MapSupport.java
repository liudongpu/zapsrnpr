package com.srnpr.zapcom.basesupport;

import java.util.ArrayList;
import java.util.List;

import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

public class MapSupport implements IBaseInstance {

	public final static MapSupport INSTANCE = new MapSupport();

	/**
	 * 获取序列化的map
	 * @param mDataMap
	 * @return
	 */
	public MDataMap upSerializeMap(MDataMap mDataMap) {

		MDataMap mReturnMap = new MDataMap();

		for (String sKey : mDataMap.keySet()) {

			mReturnMap.put(FormatHelper.upReplaceSerialize(sKey),
					mDataMap.get(sKey));
		}

		return mReturnMap;

	}

	/**
	 * 转换MAPlist到list结构体
	 * @param maps
	 * @param sSort
	 * @return
	 */
	public List<List<String>> convertMapsToLists(List<MDataMap> maps,
			String sSort) {
		List<List<String>> lReturnsArrayLists = new ArrayList<List<String>>();

		String[] sort = sSort.split(",");

		for (MDataMap map : maps) {
			List<String> list = new ArrayList<String>();
			for (String sKey : sort) {
				list.add(map.get(sKey));
			}

			lReturnsArrayLists.add(list);
		}

		return lReturnsArrayLists;

	}

}
