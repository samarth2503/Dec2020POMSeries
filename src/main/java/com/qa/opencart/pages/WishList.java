package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class WishList {
	
	public ElementUtil element;
	public WebDriver driver;
	
	public WishList(WebDriver driver)
	{
		this.driver=driver;
		element= new ElementUtil(this.driver);
	}
	
	private By rows = By.xpath("//div[@id='content']//table//tbody//tr//td");
	private By whishListheader = By.xpath("//div[@id='content']//h2");
	private By wishListTable= By.xpath("//div[@class='table-responsive']//table");
	private By continueBtn = By.xpath("//a[contains(text(),'Continue')]");
	
	public boolean verifyProductDetails()
	{
		boolean check1=false;
		
		element.waitForVisibilityofElement(whishListheader, 60);
		
		check1=element.waitForVisibilityofElement(wishListTable, 70);
		
		int row = element.getElements(rows).size();
		
		System.out.println("------- Printing Table Data -----");
		for(int i=2;i<=row-1;i++)
		{
			String content = driver.findElement(By.xpath("//div[@id='content']//table//tbody//tr//td['"+i+"']")).getText();	
			System.out.println(content);
		}
		
		element.doClick(continueBtn);
		
		if(check1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
