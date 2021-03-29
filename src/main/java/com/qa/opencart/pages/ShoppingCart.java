package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class ShoppingCart {
	
	
	public ElementUtil element;
	WebDriver driver;
	
	public ShoppingCart(WebDriver driver)
	{
		this.driver=driver; 
		element = new ElementUtil(driver);
	}
	
	private By ShoppingCartheader = By.cssSelector("#content h1");
	private By ShoppingCartTable = By.cssSelector(".table table-bordered");
	private By productImage =By.xpath("(//div[@id='content']//table[@class='table table-bordered']//tbody)[1]//tr//td[1]");
	private By productName = By.xpath("(//div[@id='content']//table[@class='table table-bordered']//tbody)[1]//tr//td[2]");
	private By unitprice = By.xpath("(//div[@id='content']//table[@class='table table-bordered']//tbody)[1]//tr//td[5]");
	private By totalprice = By.xpath("(//div[@id='content']//table[@class='table table-bordered']//tbody)[1]//tr//td[6]");
	private By Quantity = By.xpath("(//div[@id='content']//table[@class='table table-bordered']//tbody)[1]//tr//td[4]//input");
	private By footerTable = By.xpath("(//table[@class='table table-bordered'])[3]//tr[1]//td");
	private By tableRow = By.xpath("//table[@class='table table-bordered']//tr");
	private By cartButton = By.xpath("(//div[@id='cart']//button)[1]");
	private By closeBtn = By.xpath("//button[@title='Remove']");

	
	public String ShoppingCartHeader()
	{
		return element.getElementText(ShoppingCartheader);
	}
	
	public boolean ShoppingCartProduct(String product,String quantity,String UnitPrice,String totalPrice)
	{
		boolean check1,check2,check3,check4=true;
		
		element.waitForVisibilityofElement(ShoppingCartTable, 20);
		
		String pn = element.getElementText(productName);
		
		if(pn.equals(product))
		{
			System.out.println("Product Name is equal");
			check1=true;
		}
		
		else
		{
			System.out.println("Product Name is not Equal");
			check1=false;
		}
		
		String qty = element.getElementText(Quantity);
		
		if(qty.equals(quantity))
		{
			System.out.println("Quantity is equal");
			check2=true;
		}
		else
		{
			System.out.println("Qunatity is not equal");
			check2=true;
		}
		
		String ut = element.getElementText(unitprice);
		
		if(ut.equals(UnitPrice))
		{
			System.out.println("Unit Price is equal");
			check3=true;
		}
		else
		{
			System.out.println("Unit Price is not equal");
			check3=false;
		}
		
		String tp = element.getElementText(totalprice);
		
		if(tp.equals(totalPrice))
		{
			System.out.println("Total Price is equal");
			check4=true;
		}
		else
		{
			System.out.println("Total Price is not equal");
			check4=false;
		}
		
		if(check1&&check2&&check3&&check4)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	
	public Map<String,String> footerCartData()
	{
		int rows = element.getElements(tableRow).size();
		
		HashMap<String,String> hm = new HashMap<String,String>();
		
		for(int i=1;i<=rows;i++)
		{
			String key = driver.findElement(By.xpath("//table[@class='table table-bordered']//tr['"+i+"']//td[1]//strong")).getText();
			String value = driver.findElement(By.xpath("//table[@class='table table-bordered']//tr['"+i+"']//td[2]")).getText();
			
			hm.put(key, value);
		}
		
		return hm;
	}
	
	public void deleteCart()
	{
		element.doClick(cartButton);
		element.doClick(closeBtn);
	}

}
