package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;
import com.qa.automation.utils.LoggerUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	private BaseTest baseTest;

	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement usernameTxtFld;

	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement passwordTxtFld;

	@AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")
	private MobileElement loginBtn;
	
	//android.view.ViewGroup[@content-desc="test-Error message"]/android.widget.TextView
	@AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errTxt;

	public LoginPage(BaseTest baseTest) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
	}
	LoggerUtility logUtility = new LoggerUtility();
	public LoginPage enterUserName(String username) {
		logUtility.log().info(" username {}", username);
		baseTest.clearTextFeild(usernameTxtFld);
		baseTest.sendKeys(usernameTxtFld, username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		logUtility.log().info(" password {}", password);
		baseTest.clearTextFeild(passwordTxtFld);
		baseTest.sendKeys(passwordTxtFld, password);
		return this;
	}

	public ProductsPage pressLoginBtn() {
		logUtility.log().info(" login clicked ");
		baseTest.onClick(loginBtn);
		
		MenuPage menuPage= new MenuPage(baseTest);
		return new ProductsPage(baseTest,menuPage);
	}

	public ProductsPage login(String username, String password) {
		logUtility.log().info(" login username {} password {} ",username ,password);
		enterUserName(username);
		enterPassword(password);
		return pressLoginBtn();
	}

	public String errorMsg() {
		String value=  baseTest.getAttributeValue(errTxt, "text");
		logUtility.log().info(" error value {} ",value );
		return value;
	}
	public Void pressLoginBtnInvalid() {
		System.out.println("Login page "+this.getClass()+" method name pressLoginBtn");
		baseTest.onClick(loginBtn);
		return null;
		
	}

}
