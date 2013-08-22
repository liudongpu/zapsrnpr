package com.srnpr.zapweb.webcomponent;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * @author srnpr 树组件
 * 
 *         zw_s_show=显示的子窗口的来源&zw_s_data=树的数据源&zw_s_keycode=输入的Key&zw_s_keyvalue
 *         =输入的值&zw_s_table=操作的关联表&zw_s_tablekey=关联表的key&zw_s_tablevalue=关联表的值
 * 
 * 
 */
public class ComponentTree extends RootComponent {

	public String upListText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap, 3);
	}

	private String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());
		MWebHtml mBaseDivHtml = new MWebHtml("div");

		mBaseDivHtml.addChild("hidden", "id", mWebField.getPageFieldName(),
				"name", mWebField.getPageFieldName(), "value",
				mWebField.getPageFieldValue(), WebConst.CONST_WEB_FIELD_EXTEND
						+ "data", mSetMap.get("data"));

		// 修改模式
		if (iType == 5) {

			mBaseDivHtml.addChild("button", "id", mWebField.getPageFieldName()
					+ "_select", "class", "btn btn-small", "onclick",
					"zapadmin.window_url('" + mSetMap.get("show")
							+ "?zw_s_fieldname=" + mWebField.getPageFieldName()
							+ "')", "value", bInfo(969901001));
		}
		MWebHtml mUl = mBaseDivHtml.addChild("ul");

		if (StringUtils.isNotEmpty(mWebField.getPageFieldValue())) {
			mUl.addChild("li");
		}

		mBaseDivHtml.addChild("script",
				"require(['zapadmin/js/zapadmin_tree'],function(a){a.init_window('"
						+ mWebField.getPageFieldName() + "');});");

		// return sBuilder.toString();
		return mBaseDivHtml.upString();
	}

	/**
	 * 实际执行操作
	 * @param mWebField
	 * @param mDataMap
	 * @param iType
	 * @return
	 */
	private MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {
		MWebResult mResult = new MWebResult();

		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());

		DbUp.upTable(mSetMap.get("table")).delete(mSetMap.get("tablekey"),
				mDataMap.get(mSetMap.get("keycode")));

		if (StringUtils.isNotEmpty(mDataMap.get(mSetMap.get("keyvalue")))) {

			for (String sValue : mDataMap.get(mSetMap.get("keyvalue")).split(
					",")) {

				DbUp.upTable(mSetMap.get("table")).insert(
						mSetMap.get("tablekey"),
						mDataMap.get(mSetMap.get("keycode")),
						mSetMap.get("tablevalue"), sValue);

			}

		}

		return mResult;
	}

	public String upAddText(MWebField mWebField, MDataMap mDataMap) {

		return upText(mWebField, mDataMap, 1);
	}

	public MWebResult inAdd(MWebField mWebField, MDataMap mDataMap) {
		return inDo(mWebField, mDataMap, 1);
	}

	public MWebResult inEdit(MWebField mWebField, MDataMap mDataMap) {

		return inDo(mWebField, mDataMap, 5);
	}

	public String upEditText(MWebField mWebField, MDataMap mDataMap) {
		return upText(mWebField, mDataMap, 5);
	}

	public MWebResult inDelete(MWebField mWebField, MDataMap mDataMap) {
		return inDo(mWebField, mDataMap, 4);
	}

}
