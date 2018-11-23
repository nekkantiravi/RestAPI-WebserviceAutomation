package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.Configurations.ResourceHelper;

public class TestBase {

	public Properties prop;
	public FileInputStream file;
	
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_500=500;
	public int RESPONSE_STATUS_CODE_400=400;
	public int RESPONSE_STATUS_CODE_401=401;
	public int RESPONSE_STATUS_CODE_201=201;
	public int RESPONSE_STATUS_CODE_403=403;

	public TestBase() {

		try {
			prop = new Properties();
			String filepath = ResourceHelper.getResourcepath("/src/main/java/com/qa/config/config.properties");
			file = new FileInputStream(filepath);

			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
