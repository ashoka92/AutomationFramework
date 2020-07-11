package com.qa.automation.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ReadMessagePage {
	private BaseTest baseTest;
	
	AppiumDriver<MobileElement> driver;

	@AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@content-desc=\"Conversation list\"]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.TextView[1]")
	private MobileElement messagePath;
	
	@AndroidFindBy(id = "android:id/list")
	MobileElement mobile;

	public ReadMessagePage(BaseTest baseTest) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.driver = baseTest.getDriver();
		this.baseTest = baseTest;
	}

	public List<String> readMmessage(){
		List<String> messgaeList = new ArrayList<String>();
		messagePath.click();
		List<MobileElement> findElementsByClassName = mobile.findElementsByClassName("android.support.v7.widget.RecyclerView");
		
	//.ReadMessagePag//	findElementsByClassName.get(1).getAttribute("")
		
		List<MobileElement> elements =  driver.findElementsById("android:id/list");
		for (MobileElement mobileElement : elements) {
			messgaeList.add(baseTest.getAttributeValue(mobileElement, "content-des"));
		}
		return messgaeList;
		
	}


}
