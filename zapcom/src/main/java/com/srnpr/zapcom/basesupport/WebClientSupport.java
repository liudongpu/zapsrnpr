package com.srnpr.zapcom.basesupport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseCreate;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopConst;

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

	/**
	 * 根据链接获取post数据
	 * 
	 * @param sUrl
	 * @param mDataMap
	 * @return
	 * @throws Exception
	 */
	public static String upPost(String sUrl, MDataMap mDataMap)
			throws Exception {

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		// nvps.add(new BasicNameValuePair("charset",));

		for (String sKey : mDataMap.keySet()) {
			nvps.add(new BasicNameValuePair(sKey, mDataMap.get(sKey)));
		}

		HttpEntity httpEntity = new UrlEncodedFormEntity(nvps,
				TopConst.CONST_BASE_ENCODING);

		return poolRequest(sUrl, httpEntity);

	}

	static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;

	/**
	 * post请求 该请求调用的是连接池功能
	 * 
	 * @param sUrl
	 * @param httpEntity
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String poolRequest(String sUrl, HttpEntity httpEntity)
			throws ClientProtocolException, IOException {

		String sReturnString = "";

		if (poolingHttpClientConnectionManager == null) {

			poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

			// Increase max total connection to 200
			poolingHttpClientConnectionManager.setMaxTotal(200);
			// Increase default max connection per route to 20
			poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);

		}

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(poolingHttpClientConnectionManager)
				.build();

		HttpPost httppost = new HttpPost(sUrl);
		httppost.setEntity(httpEntity);

		CloseableHttpResponse response = httpClient.execute(httppost);

		try {
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {

				sReturnString = EntityUtils.toString(resEntity);

			}
		} finally {
			response.close();
		}

		return sReturnString;

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

		CloseableHttpClient httpclient = hClientBuilder.build();

		HttpPost httppost = new HttpPost(sUrl);

		CloseableHttpResponse response = null;

		try {

			httppost.setEntity(httpEntity);

			response = httpclient.execute(httppost);

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
			response.close();

			httppost.reset();
			httpclient.close();
			httpclient = null;

		}

		return sReturnString;
	}

}
