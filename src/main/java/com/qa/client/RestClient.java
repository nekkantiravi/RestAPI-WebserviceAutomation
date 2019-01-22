package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// 1.Get method without headers

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget); // hit the get url
		return closeableHttpResponse;
	}

	// Get method with headers

	public CloseableHttpResponse get(String url, HashMap<String, String> Headermap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request

		for (Map.Entry<String, String> entry : Headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget); // hit the get url
		return closeableHttpResponse;
	}

	// 2. Post method

	public CloseableHttpResponse post(String url, String entityString) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); // http post request
		httppost.setEntity(new StringEntity(entityString)); // for payload
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httppost); // hit the Post URL
		return closablehttpresponse;

	}

	// 3. Put method

	public CloseableHttpResponse put(String url, String entityString) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url); // http put request
		httpput.setEntity(new StringEntity(entityString)); // for payload
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpput);
		return closablehttpresponse;
	}
}
