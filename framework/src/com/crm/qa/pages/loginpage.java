package com.crm.qa.pages;

import java.io.IOException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.Baseclass;
import com.crm.qa.base.Data;

public class loginpage extends Baseclass {
	
	@FindBy(name="username")
	WebElement username;
	@FindBy(name="password")
	WebElement password;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement login;
	@FindBy(xpath = "//img[@class='img-responsive']")
	WebElement crmlogo;
	public loginpage()  {
		PageFactory.initElements(Data.driver, this);
	}
	public String validateloginpageTitle() {
		return Data.driver.getTitle();
	}
	public  boolean validateimage() {
		return crmlogo.isDisplayed();
	}
	public void login(String un,String pwd) throws IOException, InterruptedException {
		username.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(5000);
		login.click();
	}
}
