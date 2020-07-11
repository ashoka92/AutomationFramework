package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingPage {
	private BaseTest baseTest;
	
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private MobileElement logoutBtn;
	
	public SettingPage(BaseTest baseTest) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
	}
	
	public LoginPage pressLogout() {
		System.out.println("Setting Page  "+this.getClass()+" method name pressLogout");
		baseTest.onClick(logoutBtn);
		return new LoginPage(baseTest);
	}

}
