package com.qa.automation.base;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.utils.LoggerUtility;
import com.qa.automation.utils.TestUtilsConstant;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {
	

	protected ThreadLocal <AppiumDriver<MobileElement>> driver = new ThreadLocal<AppiumDriver<MobileElement>>();
	protected ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
//	public static Logger log= LogManager.getLogger(BaseTest.class.getName());
	
	
	public BaseTest() {
		
	}
	LoggerUtility logUtility = new LoggerUtility();
	
	 public AppiumDriver<MobileElement> getDriver() {
		  return driver.get();
	  }
	  
	  public void setDriver(AppiumDriver<MobileElement> driver2) {
		  driver.set(driver2);
	  }
	  
	  public Properties getProps() {
		  return props.get();
	  }
	  
	  public void setProps(Properties props2) {
		  props.set(props2);
	  }
	  
	  public String getPlatform() {
		  return platform.get();
	  }
	  
	  public void setPlatform(String platform2) {
		  platform.set(platform2);
	  }
	  
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  
	  public String getDeviceName() {
		  return deviceName.get();
	  }
	  
	  public void setDeviceName(String deviceName2) {
		  deviceName.set(deviceName2);
	  }

	public void initializeDriver(String platformName, String platformVersion, String deviceName, String udid, String systemPort, String chromeDriverPort) {
		Properties props = new Properties();
		InputStream inputStream = null;
		AppiumDriver<MobileElement> driver = null;
		URL url = null;
		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		ThreadContext.put("ROUTINGKEY", strFile);
		logUtility.log().info("log path: " + strFile);
		try {
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			setProps(props);
			logUtility.log().info("Properties ke value {}", props.toString());
			setPlatform(platformName);
			setDeviceName(deviceName);;
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
			/*
			 * desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
			 * props.getProperty(TestUtilsConstant.ANDROID_AUTOMATION_NAME));
			 */
			
			  desiredCapabilities.setCapability(TestUtilsConstant.APP_PACKAGE,
					  props.getProperty(TestUtilsConstant.ANDROID_APP_PACKAGE));
					  desiredCapabilities.setCapability(TestUtilsConstant.APP_ACTIVITY,
					  props.getProperty(TestUtilsConstant.ANDROID_APP_ACTIVITY));
			  
			desiredCapabilities.setCapability("systemPort", props.getProperty("systemPort"));
			desiredCapabilities.setCapability("chromeDriverPort", props.getProperty("chromeDriverPort"));
			/*
			 * String androidAppUrl =
			 * getClass().getResource(props.getProperty(TestUtilsConstant.
			 * ANDROID_APP_LOCATION)).getFile();
			 * System.out.print("Application Location "+androidAppUrl);
			 * desiredCapabilities.setCapability("app", androidAppUrl);
			 * desiredCapabilities.setCapability("avd", deviceName);
			 */

			url = new URL(this.props.get().getProperty(TestUtilsConstant.APPIUM_URL));
			logUtility.log().info("Desired capabilitoues {}", desiredCapabilities.toJson());
			driver = new AppiumDriver<MobileElement>(url, desiredCapabilities);
			setDriver(driver);
			String sessionId = driver.getSessionId().toString();
			logUtility.log().info("Session Id {}", sessionId.toString());
			System.out.println("Session Id " + sessionId);
		} catch (Exception e) {
			logUtility.log().error("Exception while loading driver device name {} exception {}",deviceName,e );
			e.printStackTrace();
		}

	}

	public void waitForVisibility(MobileElement mobileElement) {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), TestUtilsConstant.WAIT);
		webDriverWait.until(ExpectedConditions.visibilityOf(mobileElement));
	}

	public void onClick(MobileElement mobileElement) {
		waitForVisibility(mobileElement);
		mobileElement.click();
	}

	public void sendKeys(MobileElement mobileElement, String key) {
		waitForVisibility(mobileElement);
		mobileElement.sendKeys(key);
	}

	public String getAttributeValue(MobileElement mobileElement, String attribute) {
		waitForVisibility(mobileElement);
		return mobileElement.getAttribute(attribute);

	}

	public void clearTextFeild(MobileElement mobileElement) {
		waitForVisibility(mobileElement);
		mobileElement.clear();
	}

	public String getText(MobileElement mobileElement, String msg) {
		String txt = null;
		switch (getPlatform()) {
		case "Android":
			txt = getAttributeValue(mobileElement, "text");
			break;
		case "iOS":
			txt = getAttributeValue(mobileElement, "label");
			break;
		}
		return txt;
	}

	public void qiutDriver() {
		logUtility.log().info("Quit Driver for device Name {}", getDeviceName());
		this.driver.get().quit();
	}

	public void closeApp() {
		logUtility.log().info("Closing App for device Name {}", getDeviceName());
		this.driver.get().closeApp();
	}

	public void launchApp() {
		logUtility.log().info("Launching App for device Name {}", getDeviceName());
		this.driver.get().launchApp();
	}
}
