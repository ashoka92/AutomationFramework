package com.qa.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.json.JSONTokener;

public class DataProviderUtil {
	LoggerUtility logUtility = new LoggerUtility();
	public String loadConfigPropertiesFile(String pageData, String configfile) {
		String testddataFilename = null;
		try{	
			Properties configProperties = new Properties();
			InputStream inputStreamConfig = getClass().getClassLoader().getResourceAsStream(configfile);
			configProperties.load(inputStreamConfig);
			testddataFilename=configProperties.getProperty(pageData);
			logUtility.log().info(" Test data file name  {}", testddataFilename);
		}catch (Exception e) {
			logUtility.log().error(" Exception while loading properties file name  {}  {}", testddataFilename,e);
		}
		return testddataFilename;
	}
	public Object[][] loadJsonObject(Object[][] data, String testddataFilename, Properties dataProperties) {

		JSONObject jsonObject;
		InputStream inputStreamTestdata = getClass().getClassLoader().getResourceAsStream(testddataFilename);
		try {
			dataProperties.load(inputStreamTestdata);
			data = new Object[dataProperties.keySet().size()][2];
		} catch (IOException e) {
			logUtility.log().error(" Exception while loading json properties file name {}  {}", testddataFilename,e);
		}
		int i = 0;
		for (String key : dataProperties.stringPropertyNames()) {
			
			String value = dataProperties.getProperty(key);
			String dataFileName = "data/" + value;
			try (InputStream datails = getClass().getClassLoader().getResourceAsStream(dataFileName)) {
				JSONTokener tokener = new JSONTokener(datails);
				jsonObject = new JSONObject(tokener);
				logUtility.log().info(" Json Object  {}", jsonObject);
				addToObjectArray(data, i, key, jsonObject);
			} catch (IOException e) {
				logUtility.log().error(" Exception while parsing json file name  {}  {}", dataFileName,e);
			}
			i++;
		}
		return data;
	}

	private void addToObjectArray(Object[][] data, int i, String key, JSONObject productValidate) {
		data[i][0] = key;
		data[i][1] = productValidate;

	}
}
