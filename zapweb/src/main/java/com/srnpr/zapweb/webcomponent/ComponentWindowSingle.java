package com.srnpr.zapweb.webcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.MapSupport;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

public class ComponentWindowSingle extends RootSimpleComponent {

	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {
		MDataMap mSetMap = upSetMap(mWebField.getSourceParam());

		MWebHtml mDivHtml = new MWebHtml("div");

		String sFieldName = mWebField.getPageFieldName();
		String sValue = mWebField.getPageFieldValue();
		if(mDataMap.containsKey(mWebField.getFieldName()))
		{
			sValue=mDataMap.get(mWebField.getFieldName());
		}
		
		String sText = "";

		String[] sSources = StringUtils.split(mSetMap.get("source_tableinfo"),
				WebConst.CONST_SPLIT_LINE);

		String sRelTable = "";
		if (mSetMap.containsKey("relevance_tableinfo")) {
			sRelTable = mSetMap.get("relevance_tableinfo");
		}

		
		//判断是否存在关联表
		if (StringUtils.isNotBlank(sRelTable)) {

			
			
			
			
		}
		else if (StringUtils.isNotEmpty(sValue)) {

			List<String> lTextList = new ArrayList<String>();

			for (MDataMap map : DbUp.upTable(sSources[0]).queryIn(sSources[2],
					"", "", new MDataMap(), -1, 0, sSources[1], sValue)) {
				lTextList.add(map.get(sSources[2]).replace(",",
						WebConst.CONST_SPLIT_DOWN));
			}

			sText = StringUtils.join(lTextList, ",");

		}

		mDivHtml.addChild("hidden", "id", sFieldName, "name", sFieldName, "value", sValue);
		
		mDivHtml.addChild("hidden", "id", sFieldName+"_show_text", "value", sText);
		

		MDataMap mClient = new MDataMap();
		mClient.inAllValues("id", sFieldName, "text", sText, "value", sValue);

		mDivHtml.addChild("script",
				"zapjs.f.require(['zapadmin/js/zapadmin_single'],function(a){a.init("
						+ MapSupport.INSTANCE.toJson(mClient) + ");});");

		return mDivHtml.upString();

	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {

		return null;
	}

}