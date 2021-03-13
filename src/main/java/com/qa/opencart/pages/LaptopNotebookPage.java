package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class LaptopNotebookPage {
	
	public ElementUtil element;
	private WebDriver driver;
	
	public LaptopNotebookPage(WebDriver driver)
	{
		this.driver= driver;
		this.element = new ElementUtil(driver);
	}

}
