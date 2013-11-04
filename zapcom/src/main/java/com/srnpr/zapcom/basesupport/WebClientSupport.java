package com.srnpr.zapcom.basesupport;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCreate;
import com.srnpr.zapcom.basehelper.JsonHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

public class WebClientSupport extends BaseClass implements IBaseCreate {

	public static WebClientSupport create() {
		return new WebClientSupport();
	}

	/**
	 * 获取请求链接
	 * 
	 * @param sUrl
	 * @param sPost
	 * @return
	 */
	public String upRequest(String sUrl, String sPost) throws Exception {

		HttpEntity httpEntity = null;

		try {
			httpEntity = new StringEntity(sPost);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return doRequest(sUrl, httpEntity);

	}

	public String upPost(String sUrl, MDataMap mDataMap) throws Exception {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		// nvps.add(new BasicNameValuePair("charset",));

		for (String sKey : mDataMap.keySet()) {
			nvps.add(new BasicNameValuePair(sKey, mDataMap.get(sKey)));
		}

		HttpEntity httpEntity = new UrlEncodedFormEntity(nvps, "utf-8");

		return doRequest(sUrl, httpEntity);

	}

	/**
	 * 获取请求
	 * 
	 * @param sUrl
	 * @param httpEntity
	 * @return
	 * @throws Exception
	 */
	public String doRequest(String sUrl, HttpEntity httpEntity)
			throws Exception {
		String sReturnString = null;
		HttpClientBuilder hClientBuilder = HttpClientBuilder.create();

		HttpClient httpclient = hClientBuilder.build();

		HttpPost httppost = new HttpPost(sUrl);
		try {

			httppost.setEntity(httpEntity);

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				sReturnString = EntityUtils.toString(resEntity);

			}
			if (resEntity != null) {

				EntityUtils.consume(resEntity);

			}

		} catch (Exception e) {
			httppost.reset();
			httpclient = null;
			e.printStackTrace();
			throw e;

		} finally {
			httppost.reset();
			httpclient = null;

		}

		return sReturnString;
	}

}
