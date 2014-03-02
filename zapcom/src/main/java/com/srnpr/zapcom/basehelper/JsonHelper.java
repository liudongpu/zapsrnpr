package com.srnpr.zapcom.basehelper;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON帮助类
 * 
 * @author srnpr
 * 
 * @param <T>
 */
public class JsonHelper<T> {

	/**
	 * 转换
	 * 
	 * @param oInput
	 * @return
	 */
	public String ObjToString(T oInput) {
		ObjectMapper om = new ObjectMapper();

		String sResponseString = null;
		try {
			sResponseString = om.writeValueAsString(oInput);
		} catch (JsonProcessingException e) {

			e.printStackTrace();

		}

		return sResponseString;
	}

	@SuppressWarnings("unchecked")
	public T StringToObj(String sInput, T t) {

		ObjectMapper om = new ObjectMapper();
		try {

			t = (T) om.readValue(sInput, t.getClass());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return t;

	}

	public T StringToObjExp(String sInput, T t) throws IOException {

		ObjectMapper om = new ObjectMapper();

		t = (T) om.readValue(sInput, t.getClass());

		return t;

	}

}
