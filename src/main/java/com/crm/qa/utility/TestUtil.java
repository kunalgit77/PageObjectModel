package com.crm.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;
import org.apache.commons.io.FileUtils;


public class TestUtil extends TestBase
{

	public static long PAGE_LOAD = 30;
	public static long IMPLICIT_WAIT = 20;
	
	public static String TESTDATA_SHEETPATH = "D:\\selinium\\Framework\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\testdata\\data.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;
	    
	public static void handleModelPopup()
    {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
    	driver.switchTo().frame(driver.findElement(By.cssSelector("div#tawkchat-message-preview-container")));
    	Actions actions = new Actions(driver);
    	actions.moveToElement(driver.findElement(By.xpath("//div[@id='tawkchat-message-preview-container']/div[2]"))).build().perform();
    	driver.findElement(By.xpath("//div[@id='tawkchat-message-preview-container']/div[1]")).click();

    }
	
	public static void logout()
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//i[@class='settings icon']"))).click().build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'Log Out')]")).click();
	}
	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream file = null;
		try{
			file = new FileInputStream(TESTDATA_SHEETPATH);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		}catch(InvalidFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i = 0; i < sheet.getLastRowNum(); i++){
			for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++){
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
}
