package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;

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

	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement loginBtn;
	
	//android.view.ViewGroup[@content-desc="test-Error message"]/android.widget.TextView
	@AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errTxt;

	public LoginPage(BaseTest baseTest) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
	}

	public LoginPage enterUserName(String username) {
		System.out.println("Login page "+this.getClass()+" method name enterUserName");
		baseTest.clearTextFeild(usernameTxtFld);
		baseTest.sendKeys(usernameTxtFld, username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		System.out.println("Login page "+this.getClass()+"  method name enterPassword");
		baseTest.clearTextFeild(passwordTxtFld);
		baseTest.sendKeys(passwordTxtFld, password);
		return this;
	}

	public ProductsPage pressLoginBtn() {
		System.out.println("Login page "+this.getClass()+" method name pressLoginBtn");
		baseTest.onClick(loginBtn);
		AppiumDriver<MobileElement> driver = baseTest.getDriver();
		MobileElement findElementById = driver.findElementById("test-Username");
		if(findElementById.isDisplayed()) {
		MenuPage menuPage= new MenuPage(baseTest);
		return new ProductsPage(baseTest,menuPage);
		}else {
			return null;
		}
	}

	public ProductsPage login(String username, String password) {
		System.out.println("Login page "+this.getClass()+" method name login");
		enterUserName(username);
		enterPassword(password);
		return pressLoginBtn();
	}

	public String errorMsg() {
		System.out.println("Login page "+this.getClass()+" method name errorMsg");
		return baseTest.getAttributeValue(errTxt, "text");
	}
	public Void pressLoginBtnInvalid() {
		System.out.println("Login page "+this.getClass()+" method name pressLoginBtn");
		baseTest.onClick(loginBtn);
		return null;
		
	}

}
