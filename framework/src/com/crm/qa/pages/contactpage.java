package com.crm.qa.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.Data;

public class contactpage  {
	@FindBy (xpath ="//a[text()='Contacts']")
	WebElement contactslink;
	@FindBy (xpath ="//a[@title='New Contact']")
	WebElement NewContactlink;
	@FindBy(id = "first_name")
	WebElement firstname;
	@FindBy(id = "surname")
	WebElement surname;
	@FindBy(xpath ="//input[@name='client_lookup']")
	WebElement company;
	@FindBy(xpath = "//select[@name='title']")
	WebElement title;
	@FindBys(@FindBy(tagName = "option")) List<WebElement> title_list;
	@FindBy(name = "category")
	WebElement value;
	@FindBys(@FindBy(tagName = "option")) List<WebElement> value_list;
	@FindBy(name = "mainpanel") WebElement frame;
	
	public contactpage() throws IOException {
		PageFactory.initElements(Data.driver, this);
	}

	public void navigateOnContactsLink() throws IOException {
		Data.driver.switchTo().frame(frame);
		Actions act= new Actions(Data.driver);
		act.moveToElement(contactslink).build().perform();
		NewContactlink.click();
	}
	public void entering_personal_details() {
		title.click();
		for (WebElement options : title_list) {
			if(options.getText().equalsIgnoreCase("dr.")) {
				options.click();
			}
		}
		firstname.sendKeys("murali");
		surname.sendKeys("dasari");
		company.sendKeys("testcase");
		value.click();
		for (WebElement  options: value_list) {
			if(options.getText().equalsIgnoreCase("Friend")) {
				options.click();
			}

		}


	}
}

