package com.crm.testcase;

import java.io.IOException;
import org.testng.annotations.Test;

import com.crm.qa.base.Baseclass;
import com.crm.qa.pages.contactpage;

import com.crm.qa.pages.loginpage;

public class Contacttestpage extends Baseclass {
	loginpage login;
	contactpage contact;
	Baseclass bc=new Baseclass();

	@Test
	public void launchingBrowser() throws IOException, InterruptedException  {
		Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
		bc.intilazation("chrome");
		login=new loginpage();
		login.login(bc.getPropertiesFile("username"), bc.getPropertiesFile("password"));
	}	 
	@Test(priority = 1)
	public void navigate_to_contacts() throws IOException {
		contact= new contactpage();
		contact.navigateOnContactsLink();
	}	

	@Test(priority = 2)	
	public void validatecreatenewcontactlink() throws Exception {
		contact.entering_personal_details();
	}

}


