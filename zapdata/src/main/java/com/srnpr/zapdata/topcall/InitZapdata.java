package com.srnpr.zapdata.topcall;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.rootclass.RootInit;
import com.srnpr.zapdata.dbcache.TableCache;
import com.srnpr.zapdata.dbdo.DataConst;

/**
 * 初始化代码
 * 
 * @author srnpr
 * 
 */
public class InitZapdata extends RootInit {
	@Override
	public boolean onInit() {

		topInitCache(new TableCache());
		checkReplication();

		return true;
	}

	@Override
	public boolean onDestory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 检查读写分离模型
	 */
	private void checkReplication() {
		DataConst.CONST_DATA_RUN_TYPE = Integer
				.parseInt(bConfig("zapdata.data_replication_type"));

		bLogInfo(968012002, DataConst.CONST_DATA_RUN_TYPE);

	}

}
