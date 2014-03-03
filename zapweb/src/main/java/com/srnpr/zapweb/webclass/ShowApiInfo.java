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

public class ShowApiInfo extends BaseClass {

	AnnotationSupport aSupport = new AnnotationSupport();

	public ShowApiInfo() {

		apiInfo = DbUp.upTable("za_apiinfo")
				.one("api_code", "4677010900010001");

		MApiModel mApiModel = DefaultApiCache.INSTANCE.upValue(apiInfo
				.get("class_name"));

		inputClass = aSupport.upModel(mApiModel.getInputClass().getName());

		reconnClass(inputClass);

		resultClass = aSupport.upModel(mApiModel.getResultClass().getName());

		reconnClass(resultClass);

	}

	private void reconnClass(MAnnotationClass mClass) {

		for (String sField : mClass.getFields().keySet()) {

			String sClassName = mClass.getFields().get(sField).getFieldClass();

			if (StringUtils.isNotEmpty(sClassName)) {

				MAnnotationClass mNew = aSupport.upModel(sClassName);

				connClass.put(sClassName, mNew);
				reconnClass(mNew);

			}
		}

	}

	private MAnnotationClass inputClass;

	private MAnnotationClass resultClass;

	private MDataMap apiInfo;

	private Map<String, MAnnotationClass> connClass = new ConcurrentHashMap<String, MAnnotationClass>();

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

}
