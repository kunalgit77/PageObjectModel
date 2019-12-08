package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.TestUtil;

public class ContactPageTest extends TestBase{
	LoginPage loginpg;
	HomePage homepg;
	ContactPage contactpg;
	TestUtil testupg;
	String sheetName = "contact";
	
	public ContactPageTest()
	{
		super();
	}
	
	@BeforeMethod
    public void setUp()
    {
   	 initialize();
   	 loginpg = new LoginPage();
   	 contactpg = new ContactPage();
   	 testupg = new TestUtil();
   	 loginpg.loginButton();
   	 homepg = loginpg.login(prop.getProperty("username"), prop.getProperty("password"));  
   	 contactpg = homepg.clickOnContactPage();
    } 
	
	@Test(priority=1)
	public void clickAndAlertTest()
	{
		contactpg.clickAndHandleAlert();
	}
	
	@Test(priority=2)
	public void clickLogoutTest()
	{
		TestUtil.logout();
	}
	
	@Test(priority=3)
	public void clickNewTest()
	{
		contactpg.clickNew();
	}
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void newContactTest(String FirstName, String LastName, String companyName) throws InterruptedException
	{
		contactpg.clickNew();
		Thread.sleep(5000);
		//contactpg.enterNewContact("Anmol", "Gaba", "Facebook");
		contactpg.enterNewContact(FirstName, LastName, companyName);
	}

	 @AfterMethod
     public void shutDown()
     {
    	 driver.quit();
     }

}

