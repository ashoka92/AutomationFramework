package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductsPage {
	private BaseTest baseTest;
	private MenuPage menuPage;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	MobileElement SLBTitle;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	MobileElement SLBPrice;

	public ProductsPage(BaseTest baseTest, MenuPage menuPage) {
		PageFactory.initElements(new AppiumFieldDecorator(baseTest.getDriver()), this);
		this.baseTest = baseTest;
		this.menuPage = menuPage;
	}

	public MenuPage getMenuPage() {
		return menuPage;
	}


	public String getSLBTitle() {
		System.out.println("Product  page " + this.getClass() + " method name getTitle");
		String title = baseTest.getAttributeValue(SLBTitle, "text");
		return title;
	}

	public String getSLBPrice() {
		System.out.println("Product  page " + this.getClass() + " method name getSLBPrice");
		String price = baseTest.getAttributeValue(SLBPrice, "text");
		return price;
	}

	public ProductDetailsPage pressProduct() {
		System.out.println("Product  page " + this.getClass() + " method name pressProduct");
		baseTest.onClick(SLBTitle);
		return new ProductDetailsPage(baseTest, menuPage);
	}

}
