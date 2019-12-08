package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.TestUtil;

public class HomePageTest extends TestBase
{

	LoginPage loginpg;
	HomePage homepg;
	ContactPage contactpg;
	
	public HomePageTest()
	{
		super();
	}
	
     @BeforeMethod
     public void setUp()
     {
    	 initialize();
    	 loginpg = new LoginPage();
    	 contactpg = new ContactPage();
    	 loginpg.loginButton();
    	 homepg = loginpg.login(prop.getProperty("username"), prop.getProperty("password"));  	 
     }

     @Test(priority=1)
     public void verifyHomePageTitleTest()
     {
    	 String homePageTitle = homepg.verifyHomePageTitle();
    	 Assert.assertEquals(homePageTitle,"Cogmento CRM");
     }
     
     @Test(priority=2)
     public void validateUserNameOnHomePage()
     {
    	 Assert.assertTrue(homepg.checkUserNameOnHomePage());
     }
     
     @Test(priority=3)
     public void verifyContactLinkTest()
     {
    	contactpg = homepg.clickOnContactPage();
     }
     
     @Test(priority=4)
     public void verifySettingTab()
     {
    	 TestUtil.logout();
     }
     
      @AfterMethod
     public void shutDown()
     {
    	 driver.quit();
     }

     
}
