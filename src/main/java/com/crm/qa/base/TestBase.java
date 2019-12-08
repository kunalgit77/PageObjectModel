package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.utility.TestUtil;
import com.crm.qa.utility.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop ;
	public static WebEventListener elist;
	public static EventFiringWebDriver fdriver;
	
	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream fc = new FileInputStream("D:\\selinium\\Framework\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fc);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialize()
	{
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.geckodriver", "D:\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		fdriver = new EventFiringWebDriver(driver);
		elist = new WebEventListener();
		fdriver.register(elist);
		driver = fdriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
}
