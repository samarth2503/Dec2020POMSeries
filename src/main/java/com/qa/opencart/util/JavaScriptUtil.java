package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void flash(WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		
		String bgcolor = element.getCssValue("backgroundColor");
		
		for(int i=0;i<2;i++)
		{
			changecolor("#00ffff",element,driver);
			changecolor(bgcolor,element,driver);
		}
	}
	
	public void changecolor(String color,WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e)
		{
			
		}
	}
	
	public void drawBorder(WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border='3px solid red'",element);
	}
	
	public String getTitleByJS(WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String title = js.executeScript("return document.title").toString();
		return title;
	}
	
	public void doJSClick(WebElement element,WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click()",element);
	}
	
	public void generateAlert(WebDriver driver,String message)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("alert('"+message+"')");
	}
	
	public void refreshPage(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("histroy.go(0)");
	}
	
	
	public void ScrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("argument[0].scrollIntoView(true)",element);
	}
	
	public void ScrollDown(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)",element);
	}
	
	

}
