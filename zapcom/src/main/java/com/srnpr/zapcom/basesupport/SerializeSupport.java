package com.srnpr.zapcom.basesupport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Blob;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.testserialize.TestClass;

public class SerializeSupport<T>  {

	public T serialize(MDataMap mDataMap, T t) {

		MDataMap mSerializeMap = MapSupport.INSTANCE.upSerializeMap(mDataMap);

		Class<?> c = null;

		try {

			for (Field field : t.getClass().getDeclaredFields()) {

				String sFieldName = FormatHelper.upReplaceSerialize(field
						.getName());

				if (mSerializeMap.containsKey(sFieldName)) {
				
					field.setAccessible(true);
					String sValue=mSerializeMap.get(sFieldName);
					
					
					String sType=field.getType().getName();
					
					if(sType.endsWith("long"))
					{
						field.setLong(t, Long.parseLong(sValue));
					}
					else if(sType.equals("int"))
					{
						field.setInt(t, Integer.parseInt(sValue));
					}
					else if(sType.equals("float"))
					{
						field.setFloat(t, Float.parseFloat(sValue));
					}
					else
					{
						field.set(t, sValue);
					}
					
					
				
				
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		/*
		 * 
		 * Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
		 * .getGenericSuperclass()).getActualTypeArguments()[0];
		 * 
		 * T t = (T) entityClass;
		 */

		return t;
	}

}
