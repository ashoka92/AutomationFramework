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
import com.qa.automation.base.BaseTest;

public class LoginAppTest {
	LoginPage loginPage;
	ProductsPage productsPage;
	BaseTest baseTest;
	JSONObject loginUsers;

	@Parameters({ "platformName", "platformVersion", "deviceName", "udid","systemPort","chromeDriverPort", "testdata" })
	@BeforeClass
	public void beforeClass(String platformName, String platformVersion, String deviceName, String systemPort, String chromeDriverPort,String testdata) throws IOException {
		baseTest = new BaseTest();
		
		this.baseTest.initializeDriver(platformName, platformVersion, deviceName, systemPort, chromeDriverPort);
		 String dataFileName = "data/"+testdata;
		  try (InputStream datails = getClass().getClassLoader().getResourceAsStream(dataFileName) ){
			  JSONTokener tokener = new JSONTokener(datails);
			  loginUsers = new JSONObject(tokener);
		  }
		
		 this.baseTest.launchApp(); 
		 
	}

	@AfterClass
	public void afterClass() {
		this.baseTest.closeApp(); 
		this.baseTest.qiutDriver();
	}

	@BeforeMethod
	public void beforeMethod() {
		this.loginPage = new LoginPage(this.baseTest);
	}
	
	 @Test
	  public void invalidUserName() {
		  loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		  loginPage.pressLoginBtn();
		  
		  String actualErrTxt = loginPage.errorMsg();
		  String expectedErrTxt = loginUsers.getJSONObject("static_validation").getString("invalid_User_Password") ;
		  
		  Assert.assertEquals(actualErrTxt, expectedErrTxt);
	  }
	 
	 @Test
	  public void invalidPassword() {
		  loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		  loginPage.pressLoginBtn();
		  		  
		  String actualErrTxt = loginPage.errorMsg();
		  String expectedErrTxt =loginUsers.getJSONObject("static_validation").getString("invalid_User_Password") ;
		  
		  Assert.assertEquals(actualErrTxt, expectedErrTxt);
	  }
	  
	  @Test
	  public void successfulLogin() {
		  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		  this.productsPage = loginPage.pressLoginBtn();
		  		  
		  String actualProductTitle = productsPage.getSLBTitle();		  
		  String expectedProductTitle =loginUsers.getJSONObject("static_validation").getString("product_title");
		  
		  Assert.assertEquals(actualProductTitle, expectedProductTitle);
	  }
}
