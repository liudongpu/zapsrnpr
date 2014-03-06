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

		return beanFactory.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T upBean(String sBeanName) {
		return (T) getBeanObject(sBeanName);
	}

	private synchronized void initBeanFactory() {

		if (beanFactory == null) {
			String[] sSpringConfig = TopUp.upConfig("zapcom.spring_bean")
					.split(",");

			BaseLog.LogInfo(this.getClass().getName(), 0, sSpringConfig);

			beanFactory = new ClassPathXmlApplicationContext(sSpringConfig)
					.getBeanFactory();

		}

	}

}
