package com.srnpr.zapcom.basehelper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.srnpr.zapcom.baseface.IBaseHelper;
import com.srnpr.zapcom.topdo.TopUp;

/**
 * @author srnpr
 * 
 */
public class BeansHelper implements IBaseHelper {

	private static BeanFactory beanFactory = null;

	private static String SyncFlag = "";

	private static Object getBeanObject(String name) {

		if (beanFactory == null) {
			new BeansHelper().initBeanFactory();
		}

		return beanFactory.getBean(name);
	}

	public static <T> T upBean(String sBeanName) {
		return (T) getBeanObject(sBeanName);
	}

	private synchronized void initBeanFactory() {
		synchronized (SyncFlag) {

			if (StringUtils.isEmpty(SyncFlag)) {

				if (beanFactory == null) {
					String[] sSpringConfig = TopUp.upConfig(
							"zapcom.spring_bean").split(",");

					beanFactory = new ClassPathXmlApplicationContext(
							sSpringConfig).getBeanFactory();
					
					SyncFlag="ok";
					
				}
			}
		}
	}

}
