package com.qa.tests;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Configurations.ResourceHelper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PutAPITest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closablehttpresponse;

	@BeforeMethod
	public void setUp() {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		url = serviceurl + apiurl;
	}

	@Test
	public void putAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restclient = new RestClient();

		// jackon API

		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("ravi", "zion resident");

		// object to json file(Marshalling)

		//mapper.writeValue(new File(ResourceHelper.getResourcepath("/src/main/java/com/qa/data/users.json")), users);

		// object to json string

		String usersjsonstring = mapper.writeValueAsString(users);
		System.out.println(usersjsonstring);

		closablehttpresponse = restclient.put(url, usersjsonstring); // call the API

		// Validate response from API:
		// Status code

		int statuscode = closablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// json string

		String responsestring = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("Response of API---->" + responsejson);

		// json to java object(un-Marshalling)

		Users userresobj = mapper.readValue(responsestring, Users.class);
		System.out.println("userresobj--->" + userresobj);
		Assert.assertTrue(users.getName().equals(userresobj.getName()));
		Assert.assertTrue(users.getJob().equals(userresobj.getJob()));
		System.out.println("name:" + users.getName());
		System.out.println(userresobj.getName());
		System.out.println(userresobj.getJob());
		System.out.println(userresobj.getId());
		System.out.println(userresobj.getUpdatedAt());
	}

}
