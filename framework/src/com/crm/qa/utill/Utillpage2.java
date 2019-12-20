package com.crm.qa.utill;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utillpage2 {
	WebDriver driver;
	
	public void switchtoframe() {
	driver.switchTo().frame("mainpanel");
	
	}
	
	public void createfolder(String folderpath) {
		File file=new File(folderpath);
		if(!file.exists()) {
			file.mkdirs();
			
		}
	}
		public String gettimestamp() {
			String timestamp="";
			Date d=new Date();
			Calendar c= Calendar.getInstance();
			c.setTime(d);
			timestamp=timestamp+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+c.get(Calendar.HOUR)
													+c.get(Calendar.MINUTE)+c.get(Calendar.MILLISECOND);
			return timestamp;
			
			
		}
		public String takescreenshot( String screenshotname) throws IOException {
			createfolder(System.getProperty("user.dir")+"/screenshot/");
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			 screenshotname =screenshotname+gettimestamp()+".ping";
			 String filewithpath=System.getProperty("user.dir")+"/screenshot/"+screenshotname;
			 File DestFile=new File(filewithpath);
			 FileUtils.moveFile(SrcFile, DestFile);
			 return filewithpath;
			 
			
		}
		
	}


