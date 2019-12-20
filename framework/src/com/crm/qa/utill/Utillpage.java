package com.crm.qa.utill;


import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.Data;

public class Utillpage  {

	public void switchtoframe() {
			Data.driver.switchTo().frame("mainpanel");
	}
	public void createFolder(String folderPath) {
		File file= new File(folderPath);
		if(!file.exists()) {
		file.mkdirs();
		}
	}
	public String getTimeStamp() {
		String timeStamp="";
		Date d = new Date();
		Calendar c= Calendar.getInstance();
		c.setTime(d);
		timeStamp=timeStamp+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+c.get(Calendar.HOUR)
						+c.get(Calendar.MINUTE)+c.get(Calendar.SECOND)+c.get(Calendar.MILLISECOND);
		return timeStamp;
	}
	public String captureScreenshot(String screenshotName) throws Exception {
		createFolder(System.getProperty("user.dir")+"/Screenshots/");
		TakesScreenshot ts= (TakesScreenshot) Data.driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		screenshotName=screenshotName+getTimeStamp()+".png";
		String destpath=System.getProperty("user.dir")+"/Screenshots/"+screenshotName;
		File dest=new File(destpath);
		FileUtils.moveFile(src, dest);
		return destpath;
	}
	
}
