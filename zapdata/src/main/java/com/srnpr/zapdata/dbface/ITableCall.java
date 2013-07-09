package com.srnpr.zapdata.dbface;

import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MStringMap;

public interface ITableCall {

	/**
	 * 查询数据
	 * 
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param mWhereMap
	 * @param iStart
	 * @param iEnd
	 * @return
	 */
	public List<Map<String, Object>> queryList(String sFields, String sOrders,
			String sWhere, MStringMap mWhereMap, int iStart, int iEnd);

	public String dataInsert(String... sParams);
	
	/**
	 * 插入数据
	 * 
	 * @param mData
	 * @return
	 */
	public String dataInsert(MStringMap mData);

	/**
	 * 执行操作
	 * 
	 * @param sSql
	 * @param mData
	 * @return
	 */
	public int dataExec(String sSql, MStringMap mData);

	

	/**
	 * 删除数据
	 * 
	 * @param mHashMap
	 * @param sWhereFields
	 * @return
	 */
	public int dataDelete(MStringMap mHashMap, String sWhereFields);

	/**
	 * 更新数据
	 * 
	 * @param mHashMap
	 * @param sUpdateFields
	 * @param sWhereFields
	 * @return
	 */
	public int dataUpdate(MStringMap mHashMap, String sUpdateFields,
			String sWhereFields);

}
