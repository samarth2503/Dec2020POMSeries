package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	public Properties prop;
	public OptionsManager opt;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver(String browserName,String version)
	{
		String browser=null;
		opt = new OptionsManager(prop);
		
		// To get BrowserName form Maven or Jenkins Parameter
		String browserEnv=System.getProperty("browser");

		if(browserEnv!=null)
		{
			// To get the browser name from choice parameter
			browser=browserEnv;
		}
		else if(prop.getProperty("browser")!=null)
		{
			// To fetch from config.properties file
			browser = prop.getProperty("browser");
		}
		
		System.out.println("Browser is "+browser);
		highlight = prop.getProperty("highlight");
		
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(opt.getChromeOptions());
			System.out.println(prop.getProperty("remote"));
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				System.out.println("Inside Remote Driver");
				init_remotedriver(browser);
				
			}
			else
			{
				//driver=new ChromeDriver();
				tldriver.set(new ChromeDriver(opt.getChromeOptions()));
			}
			
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(opt.getfirefoxOptions());
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				init_remotedriver(browser);
			}
			else
			{
				tldriver.set(new FirefoxDriver(opt.getfirefoxOptions()));
			}
			
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
		
		
		DriverFactory.getDriver().manage().window().maximize();
		DriverFactory.getDriver().manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		DriverFactory.getDriver().manage().deleteAllCookies();
	
		
		
		return DriverFactory.getDriver();
	}
	
	
	private void init_remotedriver(String browser) {
		
		if(browser.equals("chrome"))
		{
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, opt.getChromeOptions());
//			cap.setCapability("browserName", "chrome");
//			cap.setCapability("enableVNC", true);
//			cap.setCapability("browserVersion", 89.0);
			
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browser.equals("firefox"))
		{
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, opt.getfirefoxOptions());
			cap.setCapability("browserName", "firefox");
			cap.setCapability("enableVNC", true);
			cap.setCapability("browserVersion", 86.0);
			
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}


	public static synchronized WebDriver getDriver()
	{
		return tldriver.get();
	}
	
	@SuppressWarnings("unused")
	public Properties init_Prop()
	{
		prop=new Properties();
		FileInputStream inp=null;
		//String env = System.getProperty("env");
		String env = "qa";
		
		if(env==null)
		{
			try {
				inp = new FileInputStream("C:\\Users\\samarth.jain\\eclipse-workspace\\Dec2020POMSeies\\src\\test\\resources\\config\\config.properties");
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} 
		}
		else
		{
			try {
				
				switch(env) {
					
			case "qa":
				inp = new FileInputStream("C:\\Users\\samarth.jain\\eclipse-workspace\\Dec2020POMSeies\\src\\test\\resources\\config\\qa.config.properties");
				System.out.println("Inside qa");
				
				break;
				
			case "stg":
				inp = new FileInputStream("C:\\Users\\samarth.jain\\eclipse-workspace\\Dec2020POMSeies\\src\\test\\resources\\config\\stg.config.properties");
				
				break;
			case "dev":
				inp = new FileInputStream("C:\\Users\\samarth.jain\\eclipse-workspace\\Dec2020POMSeies\\src\\test\\resources\\config\\dev.config.properties");
				
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
		System.out.println("Input file is "+inp.toString());
		
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
