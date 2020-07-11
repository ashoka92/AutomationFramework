package com.qa.automation.test;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.automation.app.pages.LoginPage;
import com.qa.automation.app.pages.ProductDetailsPage;
import com.qa.automation.app.pages.ProductsPage;
import com.qa.automation.app.pages.SettingPage;
import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;

public class ProductTest {
	LoginPage loginPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	BaseTest baseTest;
	JSONObject loginUsers;

	@Parameters({ "platformName", "platformVersion", "deviceName", "systemPort","chromeDriverPort","testdata"})
	@BeforeClass
	public void beforeClass(String platformName, String platformVersion, String deviceName, String systemPort, String chromeDriverPort,String testdata)
			throws IOException {
		baseTest = new BaseTest();
		baseTest.initializeDriver(platformName, platformVersion, deviceName, systemPort,chromeDriverPort);
		String loginData = "data/"+testdata;
		try (InputStream datails = getClass().getClassLoader().getResourceAsStream(loginData)) {
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
		this.productsPage =loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
				loginUsers.getJSONObject("validUser").getString("password"));
	}
	@AfterMethod
	public void afetrMethod() {
		MenuPage menuPage = this.productsPage.getMenuPage();
		SettingPage pressMenuBar = menuPage.pressMenuBar();
		this.loginPage = pressMenuBar.pressLogout();
	}

	@Test
	public void valiDateProductPage() {
		SoftAssert softAssert= new SoftAssert();
		
		String actualTitle=this.productsPage.getSLBTitle();
		String expectedTile =this.loginUsers.getJSONObject("static_validation").getString("product_title");
		
		String actualPrice=this.productsPage.getSLBPrice();
		String expectedPrice ="$29.99";
		softAssert.assertEquals(actualTitle, expectedTile);
		softAssert.assertEquals(actualPrice, expectedPrice);
		softAssert.assertAll();
	}
	
	@Test
	public void valiDateProductDetailsPage() {
		SoftAssert softAssert= new SoftAssert();
		
		this.productDetailsPage= this.productsPage.pressProduct();	
		String actualTitle=this.productDetailsPage.getTitle();
		String expectedTile = this.loginUsers.getJSONObject("static_validation").getString("product_title");
		
		String actualDescription=productDetailsPage.getProductDescription();
		String expectedDesciption = this.loginUsers.getJSONObject("static_validation").getString("product_description");;
		
		softAssert.assertEquals(actualTitle, expectedTile);
		softAssert.assertEquals(actualDescription, expectedDesciption);
		
		this.productsPage = this.productDetailsPage.navigateToProductsPage();
		
		String actualTitleAfter=this.productsPage.getSLBTitle();
		softAssert.assertEquals(actualTitleAfter, expectedTile);
		
		softAssert.assertAll();
	}

}
