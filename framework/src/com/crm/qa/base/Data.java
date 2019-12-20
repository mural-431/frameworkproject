package com.crm.qa.base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Data {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports reports;
	public static ExtentTest mainTest;
	public static ExtentTest test;
	public static String suiteName;
	public static String testName;
	public static Status status;
	public static String method;
}
