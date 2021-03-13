package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class CommonPages {
	
	public ElementUtil element;
	private WebDriver driver;
	
	public CommonPages(WebDriver driver)
	{
		this.driver=driver;
		this.element = new ElementUtil(driver);
	}
	
	private By desktop = By.linkText("Desktops");
	private By Alldesktop = By.linkText("Show All Desktops");
	private By laptopNotebook = By.linkText("Laptops & Notebooks");
	private By ShowAllLaptopsNotebooks = By.linkText("Show All Laptops & Notebooks");
	private By components = By.linkText("Components");
	private By tablets = By.linkText("Tablets");
	private By software = By.linkText("Software");
	private By phonePDA = By.linkText("Phones & PDAs");
	private By camera = By.linkText("Cameras");
	
	public DesktopsPage naviagteToDesktop()
	{
		element.doMoveToElement(desktop);
		element.doClick(Alldesktop);
		return new DesktopsPage(driver);
	}
	
	public LaptopNotebookPage naviagteTolaptopNotebook()
	{
		element.doMoveToElement(laptopNotebook);
		element.doClick(ShowAllLaptopsNotebooks);
		return new LaptopNotebookPage(driver);
		
	}
	
	

}
