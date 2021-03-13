package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class DesktopsPage {
	
	public ElementUtil element;
	
	public DesktopsPage(WebDriver driver)
	{
		this.element= new ElementUtil(driver);
	}

}
