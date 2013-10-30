package com.srnpr.zapdata.dbface;

import java.util.List;
import java.util.Map;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbsupport.DbTemplate;

public interface ITableCall {

	public DbTemplate upTemplate();

	/**
	 * 获取list结果
	 * 
	 * @param sSql
	 * @param mWhereMap
	 * @return
	 */
	public List<Map<String, Object>> dataSqlList(String sSql, MDataMap mWhereMap);

	/**
	 * 获取执行sql 返回执行结果的一条记录
	 * 
	 * @param sSql
	 * @param mWhereMap
	 * @return
	 */
	public Map<String, Object> dataSqlOne(String sSql, MDataMap mWhereMap);

	/**
	 * 
	 * 根据查询条件取出一条数据库记录
	 * 
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param sParams
	 * @return
	 */
	public MDataMap oneWhere(String sFields, String sOrders, String sWhere,
			String... sParams);

	/**
	 * 删除
	 * 
	 * @param sParams
	 * @return
	 */
	public int delete(String... sParams);

	/**
	 * 得到一条记录 如果返回多条错误
	 * 
	 * @param sParams
	 * @return
	 */
	public MDataMap one(String... sParams);

	/**
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param mWhereMap
	 * @param iStart  如果为-1的时候则查询所有
	 * @param iNumber
	 * @return
	 */
	public List<MDataMap> query(String sFields, String sOrders, String sWhere,
			MDataMap mWhereMap, int iStart, int iNumber);

	/**
	 * 获取值
	 * 
	 * @param sField
	 * @param sWhere
	 * @param mWhereMap
	 * @return
	 */
	public Object dataGet(String sField, String sWhere, MDataMap mWhereMap);

	/**
	 * 查询所有结果集
	 * 
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param mWhereMap
	 * @return
	 */
	public List<MDataMap> queryAll(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap);
	
	
	public List<MDataMap> queryIn(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap,int iStart, int iEnd, String sFieldName, 
			String sFieldValue);
	

	/**
	 * 插入数据库 默认uid代码生成 zid自动递增
	 * 
	 * @param sParams
	 * @return
	 */
	public String insert(String... sParams);

	public List<MDataMap> queryByWhere(String... sParams);

	public List<Map<String, Object>> listByWhere(String... sParams);

	public int count(String... sParams);

	/**
	 * 更新数据 默认更新条件为zid和uid
	 * 
	 * @param mDataMap
	 * @return
	 */
	public int update(MDataMap mDataMap);

	/**
	 * 查询数据
	 * 
	 * @param sFields
	 * @param sOrders
	 * @param sWhere
	 * @param mWhereMap
	 * @param iStart
	 * @param iNumber
	 * @return
	 */
	public List<Map<String, Object>> dataQuery(String sFields, String sOrders,
			String sWhere, MDataMap mWhereMap, int iStart, int iNumber);

	/**
	 * 插入数据
	 * 
	 * @param mDataMap
	 * @return
	 */
	public String dataInsert(MDataMap mDataMap);

	/**
	 * 执行操作
	 * 
	 * @param sSql
	 * @param mDataMap
	 * @return
	 */
	public int dataExec(String sSql, MDataMap mDataMap);

	/**
	 * 查询
	 * 
	 * @param sWhere
	 * @param mWhereMap
	 * @return
	 */
	public int dataCount(String sWhere, MDataMap mWhereMap);

	/**
	 * 删除数据
	 * 
	 * @param mDataMap
	 * @param sWhereFields
	 * @return
	 */
	public int dataDelete(String sDeleteSql, MDataMap mDataMap,
			String sWhereFields);

	/**
	 * 更新数据
	 * 
	 * @param mDataMap
	 * @param sUpdateFields
	 * @param sWhereFields
	 * @return
	 */
	public int dataUpdate(MDataMap mDataMap, String sUpdateFields,
			String sWhereFields);

}
