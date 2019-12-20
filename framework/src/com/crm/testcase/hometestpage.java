package com.crm.testcase;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.Baseclass;
import com.crm.qa.pages.homepage;
import com.crm.qa.pages.loginpage;
import com.crm.qa.utill.Utillpage;

public class hometestpage  extends Baseclass{
	
	homepage home;
	loginpage login;
	Utillpage utill;
	Baseclass bc=new Baseclass();
	
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		bc.intilazation("chrome");
		 login=new loginpage();
		 login.login(bc.getPropertiesFile("username"), bc.getPropertiesFile("password"));
		 home=new homepage();
		 utill=new Utillpage();
		 
	}
	@Test(priority=1)
	public void hometest () {
		String homepagetitle=home.verifyhomepagetitle();
		Assert.assertEquals(homepagetitle,"CRMPRO","homepagetitle not matched");//


	}
	
		}
	
	

	

