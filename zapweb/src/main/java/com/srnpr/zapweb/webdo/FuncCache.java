package com.srnpr.zapweb.webdo;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebFunc;

/**
 * 
 * 函数缓存
 * @author srnpr
 *
 */
public class FuncCache extends RootCache<String, IWebFunc> {

	public synchronized void refresh() {
		for (MDataMap mDataMap : DbUp.upTable("zw_operate").queryByWhere(
				"flag_enable", "1")) {

			if (StringUtils.isNotEmpty(mDataMap.get("operate_func"))) {

				try {

					Class<?> cClass = ClassUtils.getClass(mDataMap
							.get("operate_func"));
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IWebFunc webFunc = (IWebFunc) cClass.newInstance();

						this.inElement(mDataMap.get("operate_func"), webFunc);
					}
				} catch (Exception e) {

					bLogDebug(969905001, mDataMap.get("operate_func"));
					e.printStackTrace();

				}
			}

		}

	}

}
