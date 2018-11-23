package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {
	

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp(){
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		url = serviceurl + apiurl;
         
	}

	@Test

	public void getAPITest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeableHttpResponse=restclient.get(url);

		// a.status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code------>" + statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200,"status code is not 200");

		// b.json string:	

		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("Response json from API------->" + responsejson);

		// c.All headers
		Header[] headersArrary = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headersArrary) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("All header names------->" + allheaders);
	}
}
