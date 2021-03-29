package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SerachResultPage;
import com.qa.opencart.pages.ShoppingCart;

public class BaseTest {
	
	DriverFactory df;
	public WebDriver driver;
	public Properties prop;
	public LoginPage lp;
	public AccountPage accpage;
	public SerachResultPage sp; 
	public ProductInfoPage prodInfo;
	public RegisterPage register;
	public ShoppingCart shopcart;
	
	public SoftAssert softassert = new SoftAssert();
	
	@BeforeTest
	public void setup()
	{
		df = new DriverFactory();
		prop=df.init_Prop();
		driver=df.init_driver(prop);
		driver.get(prop.getProperty("url"));
		lp=new LoginPage(driver);
		//accpage = new AccountPage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
