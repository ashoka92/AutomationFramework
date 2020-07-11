package com.qa.automation.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.json.JSONTokener;

public class DataProviderUtil {
	public String loadConfigPropertiesFile(String pageData, String configfile) {
		String testddataFilename = null;
		try{	
			Properties configProperties = new Properties();
			InputStream inputStreamConfig = getClass().getClassLoader().getResourceAsStream(configfile);
			configProperties.load(inputStreamConfig);
			testddataFilename=configProperties.getProperty(pageData);
		}catch (Exception e) {
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		for (String key : dataProperties.stringPropertyNames()) {
			
			String value = dataProperties.getProperty(key);
			String dataFileName = "data/" + value;
			try (InputStream datails = getClass().getClassLoader().getResourceAsStream(dataFileName)) {
				JSONTokener tokener = new JSONTokener(datails);
				jsonObject = new JSONObject(tokener);
				addToObjectArray(data, i, key, jsonObject);
			} catch (IOException e) {

				e.printStackTrace();
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
