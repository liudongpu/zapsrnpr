package com.srnpr.zapweb.webmethod;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ClassUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.MapHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webface.IWebMethod;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webpage.ControlPage;

/**
 * 
 * 页面调用函数类 该类一向传递给页面调用
 * 
 * @author srnpr
 * 
 */
/**
 * @author srnpr
 * 
 */
public abstract class RootMethod extends BaseClass implements IWebMethod {

	/**
	 * 获取查询
	 * 
	 * @param sTaleName
	 * @param sOrders
	 * @param sWhere
	 * @param sPrams
	 * @return
	 */
	public List<MDataMap> upDataQuery(String sTaleName, String sOrders,
			String sWhere, String... sPrams) {
		return DbUp.upTable(sTaleName).queryAll("", sOrders, sWhere,
				new MDataMap(sPrams));

	}

	public MDataMap upDataOne(String sTaleName, String sFields, String sOrders,
			String sWhere, String... sPrams) {
		return DbUp.upTable(sTaleName).oneWhere(sFields, sOrders, sWhere,
				sPrams);

	}

	/**
	 * 插入session
	 * 
	 * @param sKey
	 * @param oValue
	 */
	public void inSession(String sKey, Object oValue) {
		/*
		 * HttpServletRequest hRequest = ((ServletRequestAttributes)
		 * RequestContextHolder .getRequestAttributes()).getRequest();
		 * 
		 * if (hRequest != null) { hRequest.getSession().setAttribute(sKey,
		 * oValue);
		 * 
		 * }
		 */
		WebSessionHelper.create().inSession(sKey, oValue);
	}

	/**
	 * 获取session
	 * 
	 * @param sKey
	 * @return
	 */
	public Object upSession(String sKey) {

		/*
		 * Object oReturnObject = null; HttpServletRequest hRequest =
		 * ((ServletRequestAttributes) RequestContextHolder
		 * .getRequestAttributes()).getRequest();
		 * 
		 * if (hRequest != null) {
		 * 
		 * oReturnObject = hRequest.getSession().getAttribute(sKey);
		 * 
		 * } return oReturnObject;
		 */
		return WebSessionHelper.create().upSession(sKey);
	}

	/**
	 * 获取页面 该项目通常用于复合页面结构定义
	 * 
	 * @param sPageCode
	 * @param sSetMap
	 * @return
	 */
	public ControlPage upControlPage(String sPageCode, String sSetMap) {
		ControlPage cPage = new ControlPage();

		cPage.setWebPage(WebUp.upPage(sPageCode));
		cPage.setReqMap(new MDataMap().inUrlParams(sSetMap));
		return cPage;

	}

	/**
	 * 根据字段名称获取字段
	 * 
	 * @param listFields
	 * @param sFieldName
	 * @return
	 */
	public MWebField upFiledByFieldName(List<MWebField> listFields,
			String sFieldName) {
		MWebField mReturnField = null;

		for (MWebField mField : listFields) {
			if (sFieldName.equals(mField.getFieldName())) {
				mReturnField = mField.clone();
			}
		}

		return mReturnField;

	}

	/**
	 * 检查是否登录
	 * 
	 * @param sInput
	 * @return
	 */
	public String checkLogin(String sInput) {
		if (UserFactory.INSTANCE.checkUserLogin()) {
			return sInput;
		} else {
			return "manage/noaccess";
		}
	}

	public Object upClass(String sClassName) {
		Object oReturn = null;

		try {
			oReturn = ClassUtils.getClass(sClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return oReturn;

	}

}
