package com.qa.automation.base;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.app.pages.SettingPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage {
	private BaseTest baseTest;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement menuBar;

	public MenuPage(BaseTest baseTest) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
		
	}
	
	public SettingPage pressMenuBar() {
		System.out.println("Menu Page  "+this.getClass()+" method name pressMenuBar");
		baseTest.onClick(menuBar);
		return new SettingPage(baseTest);
	}
	
}
