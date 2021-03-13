package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class ShoppingCart {
	
	public ElementUtil element;
	
	public ShoppingCart(WebDriver driver)
	{
		element = new ElementUtil(driver);
	}
	
	By ShoppingCartheader = By.cssSelector("#content h1");
	
	
	public String ShoppingCartHeader()
	{
		return element.getElementText(ShoppingCartheader);
	}
	
	public boolean ShoppingCartProduct(String product)
	{
		return element.doIsDisplayed(By.xpath("//div[@id='content']//form//tbody//tr[2]/td[2]/a[text()='"+product+"']"));
	}

}
