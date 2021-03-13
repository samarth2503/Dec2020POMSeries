package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	public ElementUtil element;
	
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productimage = By.xpath("//div[@id='content']//ul[@class='thumbnails']//a");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPricedata = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li"); 
	private By Quantity = By.id("input-quantity");
	private By AddtoCart = By.xpath("//button[text()='Add to Cart']");
	private By successmsg = By.cssSelector("alert-success");
	private By itemBtn = By.xpath("//div[@id='cart']/button");
	
	private By viewCart = By.cssSelector("p.text-right>a:nth-of-type(1)");
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		this.element = new ElementUtil(driver);
	}
	
	public String getProductInfoHeader()
	{
		return element.getElementText(productHeader);
	}
	
	public int getProductimagecount()
	{
		return element.getElements(productimage).size();
	}
	
	public Map<String,String> getProductInformation()
	{
		
		Map<String,String> prodInfo = new HashMap<String,String>();
		
		List<WebElement> metadata = element.getElements(productMetaData);
		System.out.println("Total Product MetaData is "+metadata.size());
		
		prodInfo.put("Name", getProductInfoHeader());
		
		for(WebElement e : metadata)
		{
			String[] str=e.getText().split(":");
			prodInfo.put(str[0].trim(), str[1].trim());
		}
		
		
		// Price data
		List<WebElement> metaprice = element.getElements(productMetaData);
		
		prodInfo.put("Price", metaprice.get(0).getText());
		prodInfo.put("Ex Tax", metaprice.get(1).getText());
		
		return prodInfo;
	}
	
	public void selectQuantity(String qyt)
	{
		element.doSendKeys(Quantity, qyt);
	}
	
	public String addToCart(By locator)
	{
		element.doClick(AddtoCart);
		return element.getElementText(successmsg);
	}
	
	public ShoppingCart navigateToViewCart()
	{
		element.doClick(itemBtn);
		element.doClick(viewCart);
		return new ShoppingCart(driver);
	}

}