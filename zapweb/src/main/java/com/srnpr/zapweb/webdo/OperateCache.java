package com.srnpr.zapweb.webdo;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseface.IBaseInit;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.rootclass.RootCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webface.IWebFunc;
import com.srnpr.zapweb.webmodel.MWebOperate;

/**
 * 操作按钮缓存
 * 
 * @author srnpr
 * 
 */
public class OperateCache extends RootCache<String, MWebOperate> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.baseface.IBaseCache#refresh()
	 */
	public synchronized void refresh() {

		/*
		 * for (MDataMap mDataMap : DbUp.upTable("zw_operate").queryByWhere(
		 * "flag_enable", "1")) {
		 * 
		 * MWebOperate mWebOperate = new MWebOperate();
		 * 
		 * mWebOperate.setOperateFunc(mDataMap.get("operate_func"));
		 * mWebOperate.setOperateLink(mDataMap.get("operate_link"));
		 * mWebOperate.setOperateName(mDataMap.get("operate_name"));
		 * mWebOperate.setOperateTypeAid(mDataMap.get("operate_type_aid"));
		 * mWebOperate.setPageCode(mDataMap.get("page_code"));
		 * mWebOperate.setOperateUid(mDataMap.get("uid"));
		 * mWebOperate.setAreaTypeAid(mDataMap.get("area_type_aid"));
		 * 
		 * this.inElement(mWebOperate.getOperateUid(), mWebOperate);
		 * 
		 * }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.srnpr.zapcom.rootclass.RootCache#upOne(java.lang.Object)
	 */
	@Override
	public MWebOperate upOne(String k) {

		MWebOperate mWebOperate = null;

		MDataMap mDataMap = DbUp.upTable("zw_operate").one("flag_enable", "1",
				"uid", k);

		if (mDataMap != null) {
			mWebOperate = new MWebOperate();

			mWebOperate.setOperateFunc(mDataMap.get("operate_func"));
			mWebOperate.setOperateLink(mDataMap.get("operate_link"));
			mWebOperate.setOperateName(mDataMap.get("operate_name"));
			mWebOperate.setOperateTypeAid(mDataMap.get("operate_type_aid"));
			mWebOperate.setPageCode(mDataMap.get("page_code"));
			mWebOperate.setOperateUid(mDataMap.get("uid"));
			mWebOperate.setAreaTypeAid(mDataMap.get("area_type_aid"));

			this.inElement(mWebOperate.getOperateUid(), mWebOperate);
		} else {
			bLogError(969905001, k);
		}

		return mWebOperate;
	}

}
