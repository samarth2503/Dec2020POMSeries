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
	private By successmsg = By.xpath("//div[contains(@class,'alert-success')]");
	private By itemBtn = By.xpath("//div[@id='cart']/button");
	private By tableRow = By.xpath("//table[@class='table table-bordered']//tr");
	private By unitprice = By.xpath("//h2[starts-with(text(),'$')]");
	private By viewCart = By.cssSelector("p.text-right>a:nth-of-type(1)");
	private By NavigatetowhishList = By.xpath("//div[@class='btn-group']//button[contains(@class,'btn-default')][1]");
	private By AddToWishList = By.xpath("(//div[@class='btn-group']//button)[5]");

	
	
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
	
	
	public String addToCart()
	{
		element.doClick(AddtoCart);
		element.waitForVisibilityofElement(successmsg, 100);
		return element.getElementText(successmsg);
	}
	
	public String getUnitPrice()
	{
		return element.getElementText(unitprice).replace("$", "");
	}
	
	public String gettotalPrice(String qty)
	{
		int q = Integer.parseInt(qty);
		int unitpr = (int) Double.parseDouble(getUnitPrice());
		
		int total = q*unitpr;
		
		return String.valueOf(total);
	}
	
	public ShoppingCart navigateToViewCart()
	{
		element.doClick(itemBtn);
		collectCartData();
		element.doClick(viewCart);
		return new ShoppingCart(driver);
	}
	
	public Map<String,String> collectCartData()
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
	
	public WishList addTowishList()
	{
		element.doClick(AddToWishList);
		element.doClick(NavigatetowhishList);
		
		return new WishList(driver);
	}
	
}
