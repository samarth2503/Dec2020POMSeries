package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class SerachResultPage {
	
	private WebDriver driver;
	private By searchItemResult= By.cssSelector("div.product-layout div.product-thumb");
	private By resultItem = By.cssSelector("div.product-thumb h4 a");
	private By cartBtn = By.xpath("//div[@id='cart']");
	
	public ElementUtil element;
	
	public SerachResultPage(WebDriver driver)
	{
		this.driver = driver;
		this.element = new ElementUtil(this.driver);
	}
	
	public int getProductcount()
	{
		return element.getElements(searchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResult(String prodName)
	{
		List<WebElement> prodList = element.getElements(resultItem);
		
		System.out.println("Total Number of Product displayed for "+prodName+" is "+prodList.size());
		for(WebElement e : prodList)
		{
			if(e.getText().equals(prodName))
			{
				System.out.println("Product is "+e.getText());
				e.click();
				break;
			}
		}
		element.waitForVisibilityofElement(cartBtn, 10);
		
		return new ProductInfoPage(driver);
	}

}
