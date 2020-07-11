package com.qa.automation.app.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.automation.base.BaseTest;
import com.qa.automation.base.MenuPage;

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
	public String getTitle() {
		System.out.println("Product Details  page "+this.getClass()+" method name getTitle");
		String title = baseTest.getAttributeValue(productTitleTxt, "text");
		return title;
	}
	
	public String getProductDescription() {
		System.out.println("Product Details  page "+this.getClass()+" method name getProductDescription");
		String description = baseTest.getAttributeValue(productDesciprtion, "text");
		return description;
	}
	
	public ProductsPage navigateToProductsPage() {
		System.out.println("Product Details  page "+this.getClass()+" method name navigateToProductsPage");
		baseTest.onClick(backToProductbtn);
		return new ProductsPage(baseTest, menuPage);
	}
}
