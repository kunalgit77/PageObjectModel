package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.utility.TestUtil;

public class LoginPage extends TestBase
{
	//Page Factory
	@FindBy(xpath="//a[@class='btn btn-sm btn-white btn-icon btn-icon-left btn-shadow btn-border btn-rect offset-sm-top-60 offset-top-30']")
    WebElement signupBtn;

	@FindBy(xpath="//span[contains(text(),'Log In')]")
	 WebElement loginFirstBtn;
	 
    @FindBy(name="email")
    WebElement username;
    
    @FindBy(name="password")
    WebElement password;
    
    @FindBy(xpath="//div[contains(text(),'Login')]")
	WebElement loginSecondBtn;
        
    //Initialize all page factory objects
    public LoginPage()
    {
    	PageFactory.initElements(driver, this);
    }
    
    public void signupLink()
    {
    	signupBtn.click();
    }
    
    public void loginButton()
    {
    	loginFirstBtn.click();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
    }
    
    public String getTitles()
    {
    	return driver.getTitle();
    }
    
    public HomePage login(String usern, String pass)
    {
    	username.sendKeys(usern);
    	password.sendKeys(pass);
    	loginSecondBtn.click();
    	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return new HomePage();
    }
    }
