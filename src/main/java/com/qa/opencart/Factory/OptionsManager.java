package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions firefox;
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
		this.co= new ChromeOptions();
		this.firefox= new FirefoxOptions();
	}
	
	public ChromeOptions getChromeOptions()
	{
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		
		return co;
	}
	
	
	public FirefoxOptions getfirefoxOptions()
	{
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			firefox.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			firefox.addArguments("--incognito");
		}
		
		return firefox;
	}

}
