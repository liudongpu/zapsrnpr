package com.srnpr.zapweb.webdo;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebComponent;
import com.srnpr.zapweb.webface.IWebFunc;

public class ComponentCache extends RootCache<String, IWebComponent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public synchronized void refresh() {

		for (MDataMap mDataMap : DbUp
				.upTable("zw_field")
				.queryAll(
						" distinct source_code as source_code ",
						"",
						" source_code!='' and   field_type_aid=104005003 and view_code in (select view_code from zw_view where project_aid in("
								+ WebUp.upProjectId() + ") )", new MDataMap())) {

			if (StringUtils.isNotEmpty(mDataMap.get("source_code"))) {

				try {

					Class<?> cClass = ClassUtils.getClass(mDataMap
							.get("source_code"));
					if (cClass != null && cClass.getDeclaredMethods() != null) {
						IWebComponent webFunc = (IWebComponent) cClass
								.newInstance();

						this.inElement(mDataMap.get("source_code"), webFunc);
					}
				} catch (Exception e) {

					bLogError(969905001, mDataMap.get("source_code"));

					e.printStackTrace();

				}

			}
		}

	}
}
