package com.srnpr.zapweb.webdo;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebFunc;

/**
 * 
 * 函数缓存
 * 
 * @author srnpr
 * 
 *         change time :2013-9-16 20:47:01 修改加载方式为延迟加载
 * 
 */
public class FuncCache extends RootCache<String, IWebFunc> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.rootclass.RootCache#upOne(java.lang.Object)
	 */
	@Override
	public IWebFunc upOne(String sKey) {

		IWebFunc webFunc = null;

		if (StringUtils.isNotEmpty(sKey)) {

			try {

				Class<?> cClass = ClassUtils.getClass(sKey);
				if (cClass != null && cClass.getDeclaredMethods() != null) {
					webFunc = (IWebFunc) cClass.newInstance();

					this.inElement(sKey, webFunc);
				}
			} catch (Exception e) {
				bLogError(969905001, sKey);
				this.inElement(sKey, null);
				e.printStackTrace();

			}
		}

		return webFunc;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public synchronized void refresh() {

		/*
		 * for (MDataMap mDataMap : DbUp .upTable("zw_operate") .queryAll(
		 * " distinct operate_func as operate_func ", "",
		 * " operate_func!='' and   flag_enable=1 and page_code in (select page_code from zw_page where project_aid in("
		 * + WebUp.upProjectId() + ") )", new MDataMap())) {
		 * 
		 * if (StringUtils.isNotEmpty(mDataMap.get("operate_func"))) {
		 * 
		 * try {
		 * 
		 * Class<?> cClass = ClassUtils.getClass(mDataMap .get("operate_func"));
		 * if (cClass != null && cClass.getDeclaredMethods() != null) { IWebFunc
		 * webFunc = (IWebFunc) cClass.newInstance();
		 * 
		 * this.inElement(mDataMap.get("operate_func"), webFunc); } } catch
		 * (Exception e) {
		 * 
		 * bLogInfo(969905001, mDataMap.get("operate_func"));
		 * 
		 * e.printStackTrace();
		 * 
		 * } }
		 * 
		 * }
		 */

	}

}
