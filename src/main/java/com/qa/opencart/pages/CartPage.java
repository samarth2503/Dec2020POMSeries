package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class CartPage {
	
	By cartButton = By.id("cart");
	ElementUtil element;
	
	public CartPage(WebDriver driver)
	{
		element = new ElementUtil(driver);
		System.out.println("Cart page");
	}
	
	public void clickOnCart()
	{
		element.doClick(cartButton);
	}

}
