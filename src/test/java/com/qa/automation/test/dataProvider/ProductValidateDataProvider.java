package com.qa.automation.test.dataProvider;

import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.qa.automation.utils.DataProviderUtil;

public class ProductValidateDataProvider {
	Object[][] data ;
	
	@DataProvider(name = "data_provider")
	public Object[][] dataProviderMethod() {
		DataProviderUtil dataProviderUtil = new DataProviderUtil();
		String testddataFilename = null;
		Properties dataProperties = new Properties();
				
		testddataFilename = dataProviderUtil.loadConfigPropertiesFile("productpagedata","config.properties");
	
		return data =dataProviderUtil.loadJsonObject(data,testddataFilename, dataProperties);

	}

}
