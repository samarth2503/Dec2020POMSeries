package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil element;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		this.element=new ElementUtil(driver);
	}
	
	// By Locators
	// Advantages of By locators :- there no API request sent to server
	
	private By username = By.cssSelector("input#input-email");
	private By password = By.cssSelector("input#input-password");
	private By lgnBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink= By.linkText("Forgotten Password");
	
	
	//Page Actions
	public String getLoginPageTitle()
	{
		return element.waitforTitleIs(5, Constants.login_page_title);
	}
	
	public AccountPage doLogin(String un,String pwd)
	{
		element.doSendKeys(username, un);
		element.doSendKeys(password, pwd);
		element.doClick(lgnBtn);
		return new AccountPage(driver);
	}
	
	public boolean forgotPasswordLink()
	{
		return element.doIsDisplayed(forgotPwdLink);
	}
	
	
	public RegisterPage navigateToRegsiterPage()
	{
		element.doClick(registerLink);
		return new RegisterPage(driver);
	}


}
