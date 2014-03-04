package com.srnpr.zapweb.webclass;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MAnnotationClass;
import com.srnpr.zapcom.basemodel.MAnnotationField;
import com.srnpr.zapcom.basemodel.MApiModel;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.AnnotationSupport;
import com.srnpr.zapcom.topapi.DefaultApiCache;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class ShowApiInfo extends BaseClass {

	AnnotationSupport aSupport = new AnnotationSupport();

	public ShowApiInfo() {

		WebSessionHelper webSessionHelper = WebSessionHelper.create();

		String sApiCode = webSessionHelper.upRequest("apicode");
		if (StringUtils.isEmpty(sApiCode)) {
			sApiCode = "467701090001";
		}

		apiInfo = DbUp.upTable("za_apiinfo").one("api_code", sApiCode);

		listInfo = DbUp.upTable("za_apiinfo").queryByWhere("parent_code",
				apiInfo.get("parent_code"));

		if (StringUtils.length(sApiCode) == 16) {

			MApiModel mApiModel = DefaultApiCache.INSTANCE.upValue(apiInfo
					.get("class_name"));
			
			
			MDataMap mParentDataMap= DbUp.upTable("za_apiinfo").one("api_code",apiInfo.get("parent_code"));
			
			apiInfo.put("parent_name", mParentDataMap.get("api_name"));
			

			if (mApiModel != null&&mApiModel.getInputClass()!=null) {

				inputClass = aSupport.upModel(mApiModel.getInputClass()
						.getName());

				reconnClass(inputClass);

				resultClass = aSupport.upModel(mApiModel.getResultClass()
						.getName());

				reconnClass(resultClass);
			}
			else {
				inputClass=new MAnnotationClass();
				resultClass=new MAnnotationClass();
			}
			
			

			showType = "info";
		} else if (StringUtils.length(sApiCode) == 12) {
			listSub = DbUp.upTable("za_apiinfo").queryByWhere("parent_code",
					apiInfo.get("api_code"));

			showType = "list";
		}

	}

	private void reconnClass(MAnnotationClass mClass) {

		for (String sField : mClass.getFields().keySet()) {

			String sClassName = mClass.getFields().get(sField).getFieldClass();

			if (StringUtils.isNotEmpty(sClassName)) {

				MAnnotationClass mNew = aSupport.upModel(sClassName);

				if(connClass==null)
				{
					connClass= new ConcurrentHashMap<String, MAnnotationClass>();
				}
				
				connClass.put(sClassName, mNew);
				reconnClass(mNew);

			}
		}

	}

	private MAnnotationClass inputClass;

	private MAnnotationClass resultClass;

	private MDataMap apiInfo;

	private Map<String, MAnnotationClass> connClass ;

	private List<MDataMap> listInfo;

	private List<MDataMap> listSub;

	private String showType = "index";

	public MAnnotationClass getInputClass() {
		return inputClass;
	}

	public void setInputClass(MAnnotationClass inputClass) {
		this.inputClass = inputClass;
	}

	public MAnnotationClass getResultClass() {
		return resultClass;
	}

	public void setResultClass(MAnnotationClass resultClass) {
		this.resultClass = resultClass;
	}

	public Map<String, MAnnotationClass> getConnClass() {
		return connClass;
	}

	public void setConnClass(Map<String, MAnnotationClass> connClass) {
		this.connClass = connClass;
	}

	public MDataMap getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(MDataMap apiInfo) {
		this.apiInfo = apiInfo;
	}

	public List<MDataMap> getListInfo() {
		return listInfo;
	}

	public void setListInfo(List<MDataMap> listInfo) {
		this.listInfo = listInfo;
	}

	public List<MDataMap> getListSub() {
		return listSub;
	}

	public void setListSub(List<MDataMap> listSub) {
		this.listSub = listSub;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
