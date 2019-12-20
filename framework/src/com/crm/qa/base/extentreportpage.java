package com.crm.qa.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.utill.Utillpage2;

public class extentreportpage {
	Utillpage2 utill=new Utillpage2();
	
	public void extentreport() {
		utill.createfolder(System.getProperty("user.dir")+"/reporter/");
		String file= new File(System.getProperty("user.dir"))+"/Reports/";
		Data.htmlReport= new ExtentHtmlReporter(file+Data.suiteName+utill.gettimestamp()+".html");
		Data.reports= new ExtentReports();
		Data.reports.attachReporter(Data.htmlReport);
	}
	public void finilizedreport() {
		Data.reports.flush();
	}
	@BeforeSuite
	public void beforesuite(ITestContext ctx) {
		Data.suiteName=ctx.getCurrentXmlTest().getSuite().getName();
		extentreport();
	}
	@BeforeTest
	public void beforetest(ITestContext ctx) {
		Data.testName=ctx.getCurrentXmlTest().getName();
		Data.mainTest=Data.reports.createTest(Data.testName);	
	}
	@BeforeMethod
	public void beforemethod(Method method) {
		Data.method=method.getName();
		Data.test=Data.mainTest.createNode(Data.method);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, Exception {
		if(result.getStatus()==ITestResult.SUCCESS) {
			Data.test.log(Data.status.PASS, "Test case is passed "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(utill.takescreenshot(Data.method)).build());
		}else if (result.getStatus()==ITestResult.FAILURE) {
			Data.test.log(Data.status.FAIL, "Test case is failed "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(utill.takescreenshot(Data.method)).build());
		}else {
			Data.test.log(Data.status.SKIP, "Test case is skipped "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(utill.takescreenshot(Data.method)).build());
		}
	}
	@AfterSuite
	public void afterSuite() {
		finilizedreport();
	}
}