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
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		url = serviceurl + apiurl;

	}

	@Test(priority = 1)

	public void getAPITestWithOutHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeableHttpResponse = restclient.get(url);

		// a.status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code------>" + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");
	

		// b.json string:

		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("Response json from API------->" + responsejson);

		// single value assertion:
		// per_page

		String perpagevalue = TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Value of per page is: " + perpagevalue);
		Assert.assertEquals(Integer.parseInt(perpagevalue), 3);

		// Total

		String totalvalue = TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("Value of total is: " + totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);

		// get the value from json array

		String lastname = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		Assert.assertEquals(lastname, "Holt");

		System.out.println("value of lastname: " + lastname);
		String id = TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(id), 4);

		// c.All headers
		Header[] headersArrary = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headersArrary) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("All header names------->" + allheaders);
		
		System.out.println("*************************************");
	}
	
	

	@Test(priority = 2)

	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();

		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		closeableHttpResponse = restclient.get(url, headermap);

		// a.status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code------>" + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// b.json string:

		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("Response json from API------->" + responsejson);

		// single value assertion:
		// per_page

		String perpagevalue = TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Value of per page is: " + perpagevalue);
		Assert.assertEquals(Integer.parseInt(perpagevalue), 3);

		// Total

		String totalvalue = TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("Value of total is: " + totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);

		// get the value from json array

		String lastname = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		Assert.assertEquals(lastname, "Holt");

		System.out.println("value of lastname: " + lastname);
		String id = TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		Assert.assertEquals(Integer.parseInt(id), 4);

		// c.All headers
		Header[] headersArrary = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headersArrary) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("All header names------->" + allheaders);

	}
}
