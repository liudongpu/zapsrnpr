package com.srnpr.zapcom.topdo;

import com.srnpr.zapcom.baseface.IBaseConst;

/**
 * 顶级定义
 * 
 * @author srnpr
 * 
 */
class TopConst implements IBaseConst {

	/**
	 * 自定义扩展目录定义
	 */
	public static String CONST_TOP_DIR_CUSTOM = null;

	/**
	 * 定义临时目录 该目录存放初始化的配置文件夹和系统信息 <br>
	 * 每次在servlet启动的时候会强制删除掉
	 */
	public static String CONST_TOP_DIR_TEMP = null;

	/**
	 * 
	 * 程序目录 该目录在tomcat运行模式下返回的是当前应用的路径（ServletLoader初始化）
	 * <p>
	 * 如果该参数为空 则表明不为servlet启动 可能是由juit启动
	 * 
	 * @see com.srnpr.zapcom.topdo.ServerletLoader#init(javax.servlet.ServletContext)
	 *      <p>
	 *      serverlet加载时的调用
	 */
	public static String CONST_TOP_DIR_SERVLET = null;

	/**
	 * 系统默认编码 默认为utf-8
	 */
	public final static String CONST_BASE_ENCODING = "utf-8";

}
