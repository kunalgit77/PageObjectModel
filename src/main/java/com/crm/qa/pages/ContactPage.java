package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.utility.TestUtil;

public class ContactPage extends TestBase{

	@FindBy(xpath="//i[@class='checkmark icon']")
	WebElement clickAlreadyCkeckBox;
	
	@FindBy(linkText="New")
	WebElement clicknew;
	
	@FindBy(xpath="//input[@name='first_name' and @type='text']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@name='last_name' and @type='text']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@class='search' and @type='text']")
	WebElement company;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveBtn;

	 public ContactPage()
	    {
	    	PageFactory.initElements(driver, this);
	    }
	 
	 public void clickAndHandleAlert()
	 {
		 clickAlreadyCkeckBox.click();
		 driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		 try
		 {
			 
		 Alert handle = driver.switchTo().alert();
		 String alertText = handle.getText();
		 System.out.println("Alert text is: " + alertText);
		 handle.dismiss();
		 }
		 catch(NoAlertPresentException e)
		 {
			 System.out.println("Noalertpresentexception is handled");
		 }
		 
		}

	public void clickNew()
	{
		Actions action = new Actions(driver);
		action.moveToElement(clicknew).click().build().perform();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
	}
	
	public void enterNewContact(String fName, String lName, String comp) throws InterruptedException
	{
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
        firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		company.sendKeys(comp);
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		action.moveToElement(saveBtn).click().build().perform();
		Thread.sleep(5000);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);


	}
}
