package com.srnpr.zapdata.dbdo;

public enum DataEnumerConnType {

	
	 /**
	 * conncache获取的连接
	 */
	BaseConn,
	 
	 /**
	 * 获取从库链接
	 */
	CodeSlave,
	 
	 /**
	 * 获取驱动级主从分离连接
	 */
	ReplivationDriver,
	 
	 /**
	 * 分布式事务获取的连接
	 */
	TxConn
	 
	
}
