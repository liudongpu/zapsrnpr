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

	private static Object getBeanObject(String name) {

		if (beanFactory == null) {
			initBeanFactory();
		}

		return beanFactory.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T upBean(String sBeanName) {
		return (T) getBeanObject(sBeanName);
	}

	private synchronized static void initBeanFactory() {

		if (beanFactory == null) {
			String[] sSpringConfig = TopUp.upConfig("zapcom.spring_bean")
					.split(",");

			BaseLog.LogInfo(new BeansHelper().getClass().getName(), 0,
					sSpringConfig);

			beanFactory = new ClassPathXmlApplicationContext(sSpringConfig)
					.getBeanFactory();

		}

	}

}
