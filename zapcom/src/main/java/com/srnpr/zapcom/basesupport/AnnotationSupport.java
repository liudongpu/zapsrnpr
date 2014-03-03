package com.srnpr.zapcom.basesupport;

import java.lang.reflect.Field;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseannotation.ZapcomApi;
import com.srnpr.zapcom.basemodel.MAnnotationClass;
import com.srnpr.zapcom.basemodel.MAnnotationField;
import com.srnpr.zapcom.basemodel.MDataMap;

public class AnnotationSupport {

	public MDataMap upAnnotationInfo(ZapcomApi zapcomApi) {
		MDataMap mDataMap = new MDataMap();

		mDataMap.put("title", StringUtils.join(zapcomApi.value()));
		mDataMap.put("remark", StringUtils.join(zapcomApi.remark()));

		return mDataMap;
	}

	public MAnnotationClass upModel(String sClassName) {

		MAnnotationClass mAnnotationClass = new MAnnotationClass();

		Class<?> c = null;

		try {

			c = ClassUtils.getClass(sClassName);

			Class<ZapcomApi> sourceClass = ZapcomApi.class;

			if (c.isAnnotationPresent(sourceClass)) {

				MDataMap mDataMap = upAnnotationInfo(c
						.getAnnotation(sourceClass));

				mAnnotationClass.setTitle(mDataMap.get("title"));
				mAnnotationClass.setRemark(mDataMap.get("remark"));

			}

			mAnnotationClass.setClassName(c.getName());

			for (Field field : c.getDeclaredFields()) {

				// 判断是否有备注
				if (field.isAnnotationPresent(sourceClass)) {

					MAnnotationField mAnnotationField = new MAnnotationField();

					MDataMap mFielDataMap = upAnnotationInfo(field
							.getAnnotation(sourceClass));
					mAnnotationField.setFieldName(field.getName());
					mAnnotationField.setTitle(mFielDataMap.get("title"));
					mAnnotationField.setRemark(mFielDataMap.get("remark"));

					// 添加到字段列表
					mAnnotationClass.getFields().put(
							mAnnotationField.getFieldName(), mAnnotationField);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mAnnotationClass;

	}

}
