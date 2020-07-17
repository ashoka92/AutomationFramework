package com.qa.automation.test;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.automation.app.pages.LoginPage;
import com.qa.automation.app.pages.ProductsPage;
import com.qa.automation.app.pages.ReadMessagePage;
import com.qa.automation.base.BaseTest;

public class MesssageTest {
	ReadMessagePage messagePage;
	BaseTest baseTest;
	JSONObject loginUsers;

	@Parameters({ "platformName", "platformVersion", "deviceName", "udid", "systemPort","chromeDriverPort" })
	@BeforeClass
	public void beforeClass(String platformName, String platformVersion, String deviceName, String udid, String systemPort, String chromeDriverPort) throws IOException {
		baseTest = new BaseTest();
		
		this.baseTest.initializeDriver(platformName, platformVersion, deviceName, udid,systemPort, chromeDriverPort);
		/*
		 * String dataFileName = "data/loginUsers.json"; try (InputStream datails =
		 * getClass().getClassLoader().getResourceAsStream(dataFileName) ){ JSONTokener
		 * tokener = new JSONTokener(datails); loginUsers = new JSONObject(tokener); }
		 */
		
		 this.baseTest.launchApp(); 
		 
	}

	@AfterClass
	public void afterClass() {
		this.baseTest.closeApp(); 
		this.baseTest.qiutDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		this.messagePage = new ReadMessagePage(this.baseTest);
	}
	
	 @Test
	  public void readMessage() {
		messagePage.readMmessage();
	  }
	 
	
}
