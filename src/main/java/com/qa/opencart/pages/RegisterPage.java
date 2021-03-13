package com.qa.opencart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	
	public ElementUtil element;
	private WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver= driver;
		this.element = new ElementUtil(driver);
	}
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-telephone");
	private By cnfPwd = By.id("input-telephone");
	private By agrCheckbox = By.xpath("//input[@name='agree']");
	
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By Yesradiobtn = By.xpath("(//input[@name='newsletter'])[position()=1]");
	private By Noradiobtn = By.xpath("(//input[@name='newsletter'])[position()=2]");
	private By SuccessMsgHeader = By.xpath("//div[@id='content']/h1");
	private By logout = By.partialLinkText("Logout");
	private By Regsiter = By.partialLinkText("Register");
	
	public boolean registerUser(String firstname,String lastname,String email,String telephone,String password,String cnfpassword,String privacyPolicy,String subscribe)
	{
		element.doSendKeys(this.firstname, firstname);
		
		element.doSendKeys(this.lastname, lastname);
		
		element.doSendKeys(this.email, generatEmailId());
		
		element.doSendKeys(this.password, password);
		
		element.doSendKeys(this.cnfPwd, cnfpassword);
		
		if(subscribe.equals("Yes"))
		{
			element.doClick(Yesradiobtn);
		}
		else
		{
			element.doClick(Noradiobtn);
		}
		
		element.doClick(agrCheckbox);
		
		element.doClick(continueBtn);
		
		element.waitForVisibilityofElement(SuccessMsgHeader, 10);
		
		String msg = element.getElementText(SuccessMsgHeader);
		
		if(msg.equals(Constants.ACCOUNT_CREATION_SUCCESS_MSG))
		{
			element.doClick(logout);
			
			element.doClick(Regsiter);
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String generatEmailId()
	{
		Random r = new Random(1000);
		String a = String.format("%04d", r.nextInt());
		return "automationTesting"+a+"@gmail.com";
	}
	

}
