package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;

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

public class PostAPITest extends TestBase {
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

	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restclient = new RestClient();

		// Jackson API:

		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); //Expected users object

		// object to json file(Marshalling)

		mapper.writeValue(new File(ResourceHelper.getResourcepath("/src/main/java/com/qa/data/users.json")), users);

		// object to json string
		String usersjsonstring = mapper.writeValueAsString(users);
		                         
		System.out.println("Object to json o/p :" +usersjsonstring);

		closablehttpresponse = restclient.post(url, usersjsonstring); // call the API

		// Validate response from API:
		// Status code

		int statuscode = closablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_201, "status code is not 201");
		System.out.println("Status code: " +statuscode);

		// json string

		String responsestring = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("The response of API is-----> :" + responsejson);

		// json to java object(un-Marshalling)

		Users userresobj = mapper.readValue(responsestring,Users.class); //actual users object
		System.out.println("userresobj--->" + userresobj);
		Assert.assertTrue(users.getName().equals(userresobj.getName()));
		Assert.assertTrue(users.getJob().equals(userresobj.getJob()));
		System.out.println("name:" +users.getName());
		System.out.println(userresobj.getName());
		System.out.println(userresobj.getJob());
		System.out.println(userresobj.getId());
		System.out.println(userresobj.getCreatedAt());
		

	}

}
