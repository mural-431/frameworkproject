package com.crm.testcase;

import java.io.IOException;
import org.testng.annotations.Test;
import com.crm.qa.base.Baseclass;
import com.crm.qa.pages.loginpage;

public class Logintestcase extends Baseclass {
	loginpage login;
	Baseclass bc= new Baseclass();
	
	@Test
	public void setup() throws IOException {
		initializereport();
		intilazation("chrome");
		login=new loginpage();
	}
	@Test(priority = 1)
	public void logintest() throws  InterruptedException, IOException  {
		login.login(bc.getPropertiesFile("username"),bc.getPropertiesFile("password"));
	}
}
