package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	private By MyAccdrpdwn= By.xpath("//a[@title='My Account']");
	private By logoutBtn = By.xpath("//ul[contains(@class,'dropdown-menu-right')]//li//a[contains(text(),'Logout')]");
	
	private By loginBtn = By.xpath("//ul[contains(@class,'dropdown-menu-right')]//li//a[contains(text(),'Login')]");
	private By HomeBtn = By.xpath("(//ul[@class='breadcrumb']//a)[1]");
	private By ContinueBtn = By.xpath("//a[contains(text(),'Continue')]");
	private By MyAccount = By.xpath("//a[contains(text(),'My Account')]"); 
	
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
	
	public void logoutUsingdp()
	{
		element.doActionclick(MyAccdrpdwn);
		element.doClick(logoutBtn);
		element.waitForVisibilityofElement(ContinueBtn, 30);
		element.doClick(ContinueBtn);
	}
	
	public void logInUsingdp()
	{
		element.waitForElementToBeClickable(MyAccdrpdwn,100);
		element.doClick(MyAccdrpdwn);
		element.doClick(loginBtn);
	}
	
	public void goToHomePage()
	{
		element.waitForVisibilityofElement(HomeBtn, 20);
		element.doClick(HomeBtn);
	}
	
	public void gotoMyAccount()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element.doActionclick(MyAccdrpdwn);
		element.doClick(MyAccount);
	}
	
	

}
