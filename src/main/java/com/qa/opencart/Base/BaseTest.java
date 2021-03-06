package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonPages;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SerachResultPage;
import com.qa.opencart.pages.ShoppingCart;
import com.qa.opencart.pages.WishList;

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
	public CommonPages cp;
	public WishList wh;
	
	public SoftAssert softassert = new SoftAssert();
	
	@Parameters({"browser","version"})
	@BeforeTest
	public void setup(@Optional("chrome")String browserName,@Optional("90.0")String versionName)
	{
		df = new DriverFactory();
		prop=df.init_Prop();
		driver=df.init_driver(browserName,versionName);
		driver.get(prop.getProperty("url"));
		lp = new LoginPage(driver);
		cp = new CommonPages(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
