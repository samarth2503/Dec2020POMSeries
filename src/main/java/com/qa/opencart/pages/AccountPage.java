package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {
	
	ElementUtil element;
	private WebDriver driver;
	
	// By Locator
	
	By logo = By.cssSelector("#logo");
	By Accheader = By.cssSelector("#content>ul");
	By searchFeild = By.xpath("//input[@name='search']");
	By searchBtn = By.xpath("//*[@id=\"search\"]/span/button");
	
	public AccountPage(WebDriver driver)
	{
		this.element = new ElementUtil(driver);
	}
	
	public String getPageTitle()
	{
		return element.waitforTitleIs(5, Constants.Account_page_title);
	}
	
	public int getAccountpageHeaderCount()
	{
		return element.getElements(Accheader).size();
	}
	
	public List<String> getAccountpageHeaderText()
	{
		List<WebElement> headers = element.getElements(Accheader);
		
		List<String> act = new ArrayList<String>();
		
		for(WebElement e : headers)
		{
			act.add(e.getText());
		}
		return act;
	}
	
	public boolean isLogoDisplayed()
	{
		return element.getElement(logo).isDisplayed();
	}
	
	public SerachResultPage doSearch(String productName)
	{
		element.doSendKeys(searchFeild, productName);
		element.doClick(searchBtn);
		
		return new SerachResultPage(driver);
	}
	
	

}
