package com.crm.qa.pages;




import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.Data;

public class homepage {
	
	@FindBy (xpath = "//a[@title='Deals']")
	WebElement Dealslink;
	@FindBy (xpath = "//a[@title='New Deal']")
	WebElement NewDeallink;
	

	public homepage() throws IOException {
			PageFactory.initElements(Data.driver, this);
		}
		public String verifyhomepagetitle() {
			return Data.driver.getTitle();
			
		}
		
		
}

	

