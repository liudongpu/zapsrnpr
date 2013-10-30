package com.srnpr.zapdata.dbsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbface.ITableCall;

public abstract class DbCall extends BaseClass implements ITableCall {

	public String insert(String... sParams) {

		return dataInsert(new MDataMap(sParams));
	}

	public int count(String... sParams) {
		return dataCount("", new MDataMap(sParams));
	}

	public int delete(String... sParams) {
		return dataDelete("", new MDataMap(sParams), "");

	}

	public List<MDataMap> queryIn(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap, int iStart, int iEnd,
			String sFieldName, String sFieldValue) {

		if (StringUtils.isNotBlank(sFieldName)) {
			if (StringUtils.isNotEmpty(sWhere)) {
				sWhere = sWhere + " and ";
			}

			String[] sValuesStrings = StringUtils.split(sFieldValue, ",");
			List<String> lAdd = new ArrayList<String>();

			for (int i = 0, j = sValuesStrings.length; i < j; i++) {
				lAdd.add(" " + sFieldName + "=:" + sFieldName + "_"
						+ String.valueOf(i) + " ");
				mWhereMap.put(sFieldName + "_" + String.valueOf(i),
						sValuesStrings[i]);

			}

			sWhere = sWhere + " (" + StringUtils.join(lAdd, " or ") + ")";

		}

		return query(sFields, sOrders, sWhere, mWhereMap, iStart, iEnd);

	}

	public int update(MDataMap mDataMap) {

		return dataUpdate(mDataMap, "", "zid,uid");
	}

	public MDataMap one(String... sParams) {

		return oneWhere("", "", "", sParams);

	}

	public MDataMap oneWhere(String sFields, String sOrders, String sWhere,
			String... sParams) {
		List<MDataMap> rLists = queryAll(sFields, sOrders, sWhere,
				new MDataMap(sParams));

		if (rLists.size() > 0) {
			return rLists.get(0);
		} else {
			return null;
		}

	}

	public List<Map<String, Object>> listByWhere(String... sParams) {
		return dataQuery("", "", "", new MDataMap(sParams), -1, -1);
	}

	public List<MDataMap> queryAll(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap) {
		return query(sFields, sOrders, sWhere, mWhereMap, -1, -1);
	}

	public List<MDataMap> queryByWhere(String... sParams) {
		return query("", "", "", new MDataMap(sParams), -1, -1);
	}

	public List<MDataMap> query(String sFields, String sOrders, String sWhere,
			MDataMap mWhereMap, int iStart, int iEnd) {

		List<MDataMap> listDataMaps = new ArrayList<MDataMap>();

		for (Map<String, Object> mEvery : dataQuery(sFields, sOrders, sWhere,
				mWhereMap, iStart, iEnd)) {
			listDataMaps.add(new MDataMap(mEvery));
		}

		return listDataMaps;
	}

}
