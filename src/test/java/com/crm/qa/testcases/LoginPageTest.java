package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utility.TestUtil;

public class LoginPageTest extends TestBase
{
	LoginPage loginpg;
	HomePage homepg;
	
	public LoginPageTest()
	{
		super();
	}
	
     @BeforeMethod
     public void setUp()
     {
    	 initialize();
    	 loginpg = new LoginPage();
     }
    
     @Test(priority=1)
     public void verifyLoginPageTitleTest()
     {
    	 String homePageTitle = loginpg.getTitles();
    	 Assert.assertEquals(homePageTitle,"Free CRM #1 cloud software for any business large or small");
     }
     
     /*@Test(priority=2)
     public void modelPopupTest()
     {
    	 TestUtil.handleModelPopup();
     }*/

     @Test(priority=2)
     public void signupLinkTest()
     {
    	 loginpg.signupLink();
     }
     
     @Test(priority=3)
     public void loginFirstBtnTest()
     {
    	 loginpg.loginButton();
     }

     @Test(priority=4)
     public void loginTest()
     {
    	 loginpg.loginButton();
    	 driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
         homepg = loginpg.login(prop.getProperty("username"), prop.getProperty("password"));
     }
     
     @AfterMethod
     public void shutDown()
     {
    	 driver.quit();
     }
     
	
}
