package com.qa.automation.test;

import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.automation.app.pages.LoginPage;
import com.qa.automation.app.pages.ProductsPage;
import com.qa.automation.base.BaseTest;
import com.qa.automation.test.dataProvider.LoginDataProvider;

public class LoginAppTestScenerioBase {
	LoginPage loginPage;
	BaseTest baseTest;

	@Parameters({ "platformName", "platformVersion", "deviceName","systemPort","chromeDriverPort" })
	@BeforeClass
	public void beforeClass(String platformName, String platformVersion, String deviceName, String systemPort, String chromeDriverPort) throws IOException, ParseException {
		baseTest = new BaseTest();
		this.baseTest.initializeDriver(platformName, platformVersion, deviceName, systemPort, chromeDriverPort);
		this.baseTest.launchApp();
	}

	@AfterClass
	public void afterClass() {
		this.baseTest.closeApp(); 
		this.baseTest.qiutDriver();
	}

	@BeforeMethod()
	public void beforeMethod() {
		this.loginPage = new LoginPage(this.baseTest);
	}
	
	 @Test(dataProvider = "data_provider",dataProviderClass = LoginDataProvider.class)
	  public void userNameLoginTest(String jsonFilename, JSONObject loginUsers ) {
		 ProductsPage productsPage = null;
		 System.out.println("jsonFilename  "+jsonFilename);
		 System.out.println("jsonFilename  "+loginUsers);
		 // read data  
		  this.loginPage.enterUserName(loginUsers.getJSONObject("loginCredential").getString("username"));
		  this.loginPage.enterPassword(loginUsers.getJSONObject("loginCredential").getString("password"));
		 
		  //logic to seperate valid and invalid
		  
		  productsPage = this.loginPage.pressLoginBtn();
		  if(productsPage==null) {
		  String actualErrTxt = this.loginPage.errorMsg();
		  String expectedErrTxt = loginUsers.getJSONObject("static_validation").getString("invalid_User_Password") ;
		  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }else {
			  String actualProductTitle = productsPage.getSLBTitle();		  
			  String expectedProductTitle =loginUsers.getJSONObject("static_validation").getString("product_title");
			  
			  Assert.assertEquals(actualProductTitle, expectedProductTitle);
		  }
		 
	  }
	 
}
