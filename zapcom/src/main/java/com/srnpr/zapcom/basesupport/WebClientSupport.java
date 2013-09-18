package com.srnpr.zapcom.basesupport;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCreate;
import com.srnpr.zapcom.basehelper.JsonHelper;

public class WebClientSupport extends BaseClass implements IBaseCreate {

	public static WebClientSupport create() {
		return new WebClientSupport();
	}

	public String upRequest(String sUrl, String sPost) {

		HttpEntity httpEntity = null;

		try {
			httpEntity = new StringEntity(sPost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return upRequest(sUrl, httpEntity);

	}

	public String upRequest(String sUrl, HttpEntity httpEntity) {
		String sReturnString = null;

		try {

			HttpClientBuilder hClientBuilder = HttpClientBuilder.create();

			HttpClient httpclient = hClientBuilder.build();

			HttpPost httppost = new HttpPost(sUrl);

			httppost.setEntity(httpEntity);

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				sReturnString = EntityUtils.toString(resEntity);

			}
			if (resEntity != null) {

				EntityUtils.consume(resEntity);

			}

			httpclient = null;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return sReturnString;
	}

}
