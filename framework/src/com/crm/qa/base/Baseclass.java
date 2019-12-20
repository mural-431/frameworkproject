package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.crm.qa.utill.Utillpage;


public class Baseclass  {
	Utillpage util= new Utillpage();
	
	public String getPropertiesFile(Object data)  {
		Properties prop=new Properties();
		FileInputStream file;
		String propertyFile=System.getProperty("user.dir")+"//src//base.properties";
		try {
			file = new FileInputStream(propertyFile);
			prop.load(file);
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return (String) prop.get(data);
		}
	
	
	public void intilazation(String browser) {
		
		if(browser.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			  Data.driver =new ChromeDriver();
		
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 Data.driver =new FirefoxDriver();
			
		}
		Data.driver.manage().window().maximize();
		Data.driver.manage().deleteAllCookies();
		Data.driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS );
		Data.driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		Data.driver.get(getPropertiesFile("url"));
	}
	public void initializereport() {
		util.createFolder(System.getProperty("user.dir")+"/Reports/");
		String file= new File(System.getProperty("user.dir"))+"/Reports/";
		Data.htmlReport= new ExtentHtmlReporter(file+Data.suiteName+util.getTimeStamp()+".html");
		Data.reports= new ExtentReports();
		Data.reports.attachReporter(Data.htmlReport);
	} 
	public void finalizeReport() {
		Data.reports.flush();
	}
	@BeforeSuite
	public void beforeSuite(ITestContext ctx) {
		Data.suiteName=ctx.getCurrentXmlTest().getSuite().getName();
		initializereport();
	}
	@BeforeTest
	public void beforeTest(ITestContext ctx) {
		Data.testName=ctx.getCurrentXmlTest().getName();
		Data.mainTest=Data.reports.createTest(Data.testName);
	}
	@BeforeMethod
	public void beforeMethod(Method method) {
		Data.method=method.getName();
		Data.test=Data.mainTest.createNode(Data.method);
	}
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, Exception {
		if(result.getStatus()==ITestResult.SUCCESS) {
			Data.test.log(Data.status.PASS, "Test case is passed "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(util.captureScreenshot(Data.method)).build());
		}else if (result.getStatus()==ITestResult.FAILURE) {
			Data.test.log(Data.status.FAIL, "Test case is failed "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(util.captureScreenshot(Data.method)).build());
		}else {
			Data.test.log(Data.status.SKIP, "Test case is skipped "+result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(util.captureScreenshot(Data.method)).build());
		}
	}
	@AfterSuite
	public void afterSuite() {
		finalizeReport();
	}
}
