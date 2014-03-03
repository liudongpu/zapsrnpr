package com.srnpr.zapcom.basesupport;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.basemodel.MAnnotationClass;
import com.srnpr.zapcom.basemodel.MAnnotationField;
import com.srnpr.zapcom.basemodel.MDataMap;

/**
 * 注解支持类
 * 
 * @author srnpr
 * 
 */
public class AnnotationSupport {

	public MDataMap upAnnotationInfo(ZapcomApi zapcomApi) {
		MDataMap mDataMap = new MDataMap();

		if (zapcomApi != null) {
			mDataMap.put("title", StringUtils.join(zapcomApi.value()));
			mDataMap.put("remark", StringUtils.join(zapcomApi.remark()));
		}

		return mDataMap;
	}

	private final static String CONST_BASE_TYPE = "string,int,float,double,boolen,long,short";

	public MAnnotationClass upModel(String sClassName) {

		MAnnotationClass mAnnotationClass = new MAnnotationClass();

		try {

			Class<?> cInput = ClassUtils.getClass(sClassName);

			Class<ZapcomApi> sourceClass = ZapcomApi.class;

			if (cInput.isAnnotationPresent(sourceClass)) {

				MDataMap mDataMap = upAnnotationInfo(cInput
						.getAnnotation(sourceClass));

				mAnnotationClass.setTitle(mDataMap.get("title"));
				mAnnotationClass.setRemark(mDataMap.get("remark"));

			}

			mAnnotationClass.setClassName(cInput.getName());

			// 递归循环所有父类的字段
			for (Class<?> clazz = cInput; clazz != Object.class; clazz = clazz
					.getSuperclass()) {

				for (Field field : clazz.getDeclaredFields()) {

					// 判断是否有备注
					if (field.isAnnotationPresent(sourceClass)) {

						MAnnotationField mAnnotationField = new MAnnotationField();

						MDataMap mFielDataMap = upAnnotationInfo(field
								.getAnnotation(sourceClass));
						mAnnotationField.setFieldName(field.getName());
						mAnnotationField.setTitle(mFielDataMap.get("title"));
						mAnnotationField.setRemark(mFielDataMap.get("remark"));

						// mAnnotationField.setFieldClass(field.getType().toString());

						Class<?> cFieldClass = field.getType();

						String sFieldString = cFieldClass.getName();

						String sLastName = sFieldString.contains(".") ? StringUtils
								.substringAfterLast(sFieldString, ".")
								: sFieldString;

						if (StringUtils.containsIgnoreCase(CONST_BASE_TYPE,
								sLastName)) {
							mAnnotationField.setType(sLastName);
						} else {

							if (cFieldClass.isAssignableFrom(List.class)) {
								Type gt = field.getGenericType(); // 得到泛型类型
								ParameterizedType pt = (ParameterizedType) gt;
								Class<?> cGeneriClass = (Class<?>) pt
										.getActualTypeArguments()[0];

								mAnnotationField
										.setType(StringUtils.substringAfterLast(
												cGeneriClass.getCanonicalName(),
												".")
												+ "[]");
								mAnnotationField.setFieldClass(cGeneriClass
										.getName());
								// cGeneriClass.getName();

							} else {

								// field.isSynthetic()

								mAnnotationField.setType(sLastName);
								mAnnotationField.setFieldClass(sFieldString);

							}
						}

						// 添加到字段列表
						mAnnotationClass.getFields().put(
								mAnnotationField.getFieldName(),
								mAnnotationField);

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mAnnotationClass;

	}

}
