package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.Factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private Actions ac;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		ac= new Actions(this.driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	/** 
	 * Description :- This Utility will fetch Webelement and return the same
	 * @param locator
	 * @return
	 */
	
	public List<WebElement> getElements(By locator)
	{
		List<WebElement> elements = driver.findElements(locator);
		return elements;
	}
	
	public String getElementText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public WebElement getElement(By locator)
	{
		WebElement ele = driver.findElement(locator);
		
		if(Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(ele,driver);
		}
		return ele;
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public void doSendKeys(By locator,String text)
	{
		getElement(locator).clear();
		getElement(locator).sendKeys(text);
	}
	
	/**
	 * Description :- This Utility will click on element
	 * @param locator
	 */
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	/**
	 * Description :- This Utility will select option from drop down on basis of visible text
	 * @param locator
	 * @param text
	 */
	
	public void doselectByvisisbleText(By locator,String text)
	{
		WebElement ele = getElement(locator);
		Select s = new Select(ele);
		s.selectByVisibleText(text);
	}
	
	/**
	 * Description :- This Utility will select option from drop dwon on basis of Value
	 * @param locator
	 * @param value
	 */
	
	public void doselectByvalue(By locator,String value)
	{
		WebElement ele = getElement(locator);
		Select s = new Select(ele);
		s.deselectByValue(value);
	}
	
	/**
	 * Description :- This Utility will select option dropdown on basis of Index
	 * @param locator
	 * @param i
	 */
	
	public void doselectByIndex(By locator,int i)
	{
		WebElement ele = getElement(locator);
		Select s = new Select(ele);
		s.deselectByIndex(i);
	}
	
	
	/**
	 * Description:- this Utility will fetch all the Option from dropdown and store in List
	 * @param locator
	 * @return
	 */
	
	public List<String> getDropdownOptionList(By locator)
	{
		WebElement ele = getElement(locator);
		
		Select s = new Select(ele);
		
		List<WebElement> element = s.getOptions();
		
		List<String> optionList = new ArrayList<String>();
		
		for(WebElement e : element)
		{
			optionList.add(e.getText());
		}
		return optionList;
	}
	
	/**
	 * Description :- This Utility will click on Link provided by user
	 * @param locator
	 * @param value
	 */
	
	public void doLinkClick(By locator,String value)
	{
		List<WebElement> ele = getElements(locator);
		
		for(int j=0;j<ele.size();j++)
		{
			if(ele.get(j).getText().equals(value))
			{
				ele.get(j).click();
				break;
			}
		}
		
	}
	
	/**
	 * 
	 * @param locator
	 * @param timeout
	 * @param interval
	 * @return
	 */
	
	public WebElement waitForfluentWait(By locator,int timeout,int interval)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(2))										// By default 500 sec if not Duration is applied
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));			// Return Webelement
	}
	
	public WebElement waitforPresenceElement(By locator,int timeout,int interval)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout,interval);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public Alert waitforAlert(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return  wait.until(ExpectedConditions.alertIsPresent());							// It will return Alert and also will be switched to alert
	}
	
	public void acceptAlert(int timeout)
	{
		waitforAlert(timeout).accept();
	}
	
	public void dismissAlert(int timeout)
	{
		waitforAlert(timeout).dismiss();
	}
	
	public void getAlertText(int timeout)
	{
		waitforAlert(timeout);
	}
	
	public String waitforTitleIs(int timeout,String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	

	
	/********************************************* Action Class ***********************************************************/
	
	// 2 level click
	public void doMoveToElement(By locator) {
		
		ac.moveToElement(getElement(locator)).perform();
	}

	// 3 level click
	public void doMoveToElement(By locator1, By locator2) {
		
		ac.moveToElement(getElement(locator1)).perform();
		ac.moveToElement(getElement(locator2)).perform();
	}

	// 3 level with click
	public void doMoveToElement(By locator1, By locator2, By locator3) {
		
		ac.moveToElement(getElement(locator1)).perform();
		ac.moveToElement(getElement(locator2)).perform();
		getElement(locator3).click();
	}

	public void doActionclick(By locator) {
	
		ac.click(getElement(locator));									 // It will click on middle of the element
	}

	public void doActionSendKeys(By locator, String text) {
		
		ac.sendKeys(getElement(locator), text).perform(); 				// Actions class sendKeys will first perform click action on
																			// text feild and them enter the text
	}
	
	public void waitForPresenceofElement(By locator,long timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	public boolean waitForVisibilityofElement(By locator,long timeout)
	{
		boolean check=false;
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		if(el!=null)
		{
			check=true;
		}
		
		return check;
	}
	
	public void waitForElementToBeClickable(By locator,int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public String getAttribute(By Locator,String attr)
	{
		return getElement(Locator).getAttribute(attr);
	}
}
