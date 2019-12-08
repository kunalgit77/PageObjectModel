package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase
{

	@FindBy(xpath="//span[contains(text(),'kunal aggarwal')]")
	WebElement userNameValidation;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")
    WebElement contactLink;
	
	@FindBy(xpath="//span[contains(text(),'Deals')]")
    WebElement dealsLink;
	
	@FindBy(xpath="//span[contains(text(),'Tasks')]")
    WebElement taskLink;
	
	@FindBy(xpath="//span[contains(text(),'Calendar')]")
    WebElement calendarLink;
	
	 public HomePage()
	    {
	    	PageFactory.initElements(driver, this);
	    }
	 
	 public String verifyHomePageTitle()
	 {
		 return driver.getTitle();
	 }
	 
	 public boolean checkUserNameOnHomePage()
	 {
		 return userNameValidation.isDisplayed();
	 }

	  public ContactPage clickOnContactPage()
	 {
		 contactLink.click();
		 return new ContactPage();
	 }
	 
}
