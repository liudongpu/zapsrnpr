package com.srnpr.zapcom.basehelper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.srnpr.zapcom.baseclass.BaseLog;
import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.topdo.TopUp;

/**
 * @author srnpr
 * 
 */
public class BeansHelper implements IBaseHelper {

	private static BeanFactory beanFactory = null;

	private static int flagInit = 0;

	private static Object getBeanObject(String name) {

		if (flagInit < 1 || beanFactory == null) {
			flagInit = 2;
			if (flagInit == 2) {
				flagInit = 3;
				if (beanFactory == null) {
					new BeansHelper().initBeanFactory();
				}
			}
		}

		Object oReturn = null;

		// 尝试返回 如果失败二次返回
		try {
			oReturn = beanFactory.getBean(name);

		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.LogInfo(BeansHelper.class.getName(), 967912050, name);
			beanFactory = null;
			new BeansHelper().initBeanFactory();
			oReturn = beanFactory.getBean(name);

		}

		return oReturn;
	}

	@SuppressWarnings("unchecked")
	public static <T> T upBean(String sBeanName) {
		return (T) getBeanObject(sBeanName);
	}

	private synchronized void initBeanFactory() {

		if (beanFactory == null) {
			String[] sSpringConfig = TopUp.upConfig("zapcom.spring_bean")
					.split(",");

			BaseLog.LogInfo(BeansHelper.class.getName(), 967912051,
					TopUp.upConfig("zapcom.spring_bean"));

			beanFactory = new ClassPathXmlApplicationContext(sSpringConfig)
					.getBeanFactory();

		}

	}

}
