package com.qa.automation.listner;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.qa.automation.base.BaseTest;
import com.qa.automation.reports.ExtentReport;
import com.qa.automation.utils.LoggerUtility;

public class AppTestListner implements ITestListener{

	LoggerUtility logUtility = new LoggerUtility();
	@Override
	public void onTestStart(ITestResult result) {
		BaseTest base = new BaseTest();
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription())
		.assignCategory(base.getPlatform() + "_" + base.getDeviceName());	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if(result.getThrowable() != null) {
			  StringWriter sw = new StringWriter();
			  PrintWriter pw = new PrintWriter(sw);
			  result.getThrowable().printStackTrace(pw);
			  logUtility.log().error(sw.toString());
		}
		ExtentReport.getTest().log(Status.FAIL, "Test Fail");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReport.getReporter().flush();	
		
	}

}
