package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;
import com.qa.automation.utils.LoggerUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDetailsPage {
	BaseTest baseTest;
	MenuPage menuPage;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	MobileElement productTitleTxt;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
	MobileElement productDesciprtion;
	
	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
	MobileElement backToProductbtn;
	
	public ProductDetailsPage(BaseTest baseTest, MenuPage menuPage) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
		this.menuPage=menuPage;
	}
	LoggerUtility logUtility = new LoggerUtility();
	public String getTitle() {
		String title = baseTest.getAttributeValue(productTitleTxt, "text");
		logUtility.log().info(" Product Page title {}", title);
		return title;
	}
	
	public String getProductDescription() {
		String description = baseTest.getAttributeValue(productDesciprtion, "text");
		logUtility.log().info(" Product Page description {}", description);
		return description;
	}
	
	public ProductsPage navigateToProductsPage() {
		baseTest.onClick(backToProductbtn);
		logUtility.log().info(" navigate to ProductsPage ");
		return new ProductsPage(baseTest, menuPage);
	}
}
