package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	public Properties prop;
	public OptionsManager opt;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(Properties prop)
	{
		opt = new OptionsManager(prop);
		String browser = prop.getProperty("browser");
		System.out.println("Browser is "+browser);
		highlight = prop.getProperty("highlight");
		
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(opt.getChromeOptions());
			tldriver.set(new ChromeDriver(opt.getChromeOptions()));
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(opt.getfirefoxOptions());
			tldriver.set(new FirefoxDriver(opt.getfirefoxOptions()));
		}
		else if(browser.equals("Safari"))
		{
			driver=new SafariDriver();
			tldriver.set(new FirefoxDriver(opt.getfirefoxOptions()));
		}
		else
		{
			System.out.println("Browser not found....Please pass correct browser name "+browser);
		}
		
		
		DriverFactory.getDriver().manage().window().fullscreen();
		DriverFactory.getDriver().manage().deleteAllCookies();
	
		
		
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver()
	{
		return tldriver.get();
	}
	
	public Properties init_Prop()
	{
		prop=new Properties();
		FileInputStream inp=null;
		String env = System.getProperty("env");
		
		if(env==null)
		{
			try {
				inp = new FileInputStream("D:\\Selenium WorkSpace\\Dec2020POMSeies\\src\\test\\resources\\config\\config.properties");
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} 
		}
		else
		{
			try {
				
				switch(env) {
					
			case "qa":
				inp = new FileInputStream("D:\\Selenium WorkSpace\\Dec2020POMSeies\\src\\test\\resources\\config\\qa.config.properties");
				
				break;
				
			case "stg":
				inp = new FileInputStream("D:\\Selenium WorkSpace\\Dec2020POMSeies\\src\\test\\resources\\config\\stg.config.properties");
				
				break;
			case "dev":
				inp = new FileInputStream("D:\\Selenium WorkSpace\\Dec2020POMSeies\\src\\test\\resources\\config\\dev.config.properties");
				
				break;
				
			default:
				System.out.println("Please slect valid Environment");
				
				}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			prop.load(inp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return prop;
	}
	
	public String getScreenshot()
	{
		TakesScreenshot ts = ((TakesScreenshot)getDriver());
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		//File srcFile = new File(src);
		
		String path = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		File destFile = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
		
	}

}
