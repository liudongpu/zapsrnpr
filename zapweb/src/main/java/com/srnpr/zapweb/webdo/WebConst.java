package com.srnpr.zapweb.webdo;

import com.srnpr.zapcom.baseface.IBaseConst;

public class WebConst implements IBaseConst {

	/**
	 * 定义页面字段的默认左边 126022006
	 */
	public final static String CONST_WEB_FIELD_NAME = "zw_f_";

	
	/**
	 * 定义页面分页字段左边 126022016
	 */
	public final static String CONST_WEB_PAGINATION_NAME = "zw_p_";

	/**
	 * 附加在字段右边的字段  常用于处理查询的特殊定义 126022001
	 */
	public final static String CONST_WEB_FIELD_AFTER = "_zw_a_";
	
	/**
	 * 组件定义   126022003
	 */
	public final static String CONST_WEB_FIELD_COMPONENT="zw_c_";
	
	/**
	 * 扩展定义 126022005  常用于页面元素的字段的扩展
	 */
	public final static String CONST_WEB_FIELD_EXTEND="zw_e_";
	
	
	/**
	 * 设置定义 126022019  常用于设置各种属性 url用 或者url的设置参数
	 */
	public final static String CONST_WEB_FIELD_SET="zw_s_";
	
	
	
	
	/**
	 * 定义需要替换的规则表达式  如果字段含有该字段  则执行替换逻辑
	 */
	public final static String CONST_WEB_SET_REPLACE="[@";
	
	
	
	
	/**
	 * 定义session默认的key
	 */
	public final static String CONST_WEB_SESSION_KEY="session:zw:";
	
	
	/**
	 * 定义用户信息session
	 */
	public final static String CONST_WEB_SESSION_USER="session:zw:userinfo";
	
	

}
