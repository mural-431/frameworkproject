package com.crm.testcase;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.utill.Utillpage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReporters {
	Utillpage util= new Utillpage();
	@Test
	public void reports() {
		util.createFolder(System.getProperty("user.dir")+"/Reports/");
		String fileName=System.getProperty("user.dir")+"/Reports/";
		ExtentHtmlReporter htmlReport= new ExtentHtmlReporter(fileName+util.getTimeStamp()+".html");
		ExtentReports report= new ExtentReports();
		report.attachReporter(htmlReport);
		ExtentTest test=report.createTest("Extent Report Demo");
		report.flush();
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://google.com");
	
	}

}
