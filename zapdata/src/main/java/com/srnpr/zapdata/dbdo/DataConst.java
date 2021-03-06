package com.srnpr.zapdata.dbdo;

import com.srnpr.zapcom.baseface.IBaseConst;

/**
 * 数据库定义
 * @author srnpr
 *
 */
/**
 * @author srnpr
 * 
 */
public class DataConst implements IBaseConst {

	/**
	 * 从库缓存名称的开始字符串
	 */
	public final static String CONST_DATA_SLAVE_NAME = "slave:";

	/**
	 * 数据库运行模型 0为默认只连接一个库 1为数据库层读写分离 2为驱动级读写分离
	 */
	public static int CONST_DATA_RUN_TYPE = 0;

	/**
	 * 连接池最大数量
	 */
	public final static int CONST_DATA_INIT_MAX_POOL = 10;
	
	
	/**
	 * XA连接池最大连接数
	 */
	public final static int CONST_DATA_XASOURCE_MAXPOOL=5;

	/**
	 * 事务连接前的测试代码
	 */
	public final static String CONST_DATA_TEST_QUERY = "select version() ";

}
