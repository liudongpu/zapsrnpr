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
 * @author srnpr
 *
 */
public class FuncCache extends RootCache<String, IWebFunc> {

	/* (non-Javadoc)
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public synchronized void refresh() {
		
		
		
		for (MDataMap mDataMap :DbUp.upTable("zw_operate").queryAll(" distinct operate_func as operate_func ", "", " operate_func!='' and   flag_enable=1 and page_code in (select page_code from zw_page where project_aid in("+WebUp.upProjectId()+") )", new MDataMap())) {

			if (StringUtils.isNotEmpty(mDataMap.get("operate_func"))) {

				try {
					
					
									
					Class<?> cClass = ClassUtils.getClass(mDataMap
							.get("operate_func"));
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IWebFunc webFunc = (IWebFunc) cClass.newInstance();

						this.inElement(mDataMap.get("operate_func"), webFunc);
					}
				} catch (Exception e) {

					bLogInfo(969905001, mDataMap.get("operate_func"));

					e.printStackTrace();

				}
			}

		}

	}

}
