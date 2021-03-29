package com.qa.opencart.Test;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.util.Error;
import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.pages.ShoppingCart;

public class ShoppingCartTest extends BaseTest {
	
	@BeforeMethod
	public void ShoppingCartsetup()
	{
		accpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		sp=accpage.doSearch("iphone");
		prodInfo=sp.selectProductFromResult("iPhone");
		
	}
	
	@Test(priority=1)
	public void verifyProduct()
	{
		Map<String,String> hm=prodInfo.getProductInformation();
		prodInfo.selectQuantity("2");
		prodInfo.addToCart();
		shopcart=prodInfo.navigateToViewCart();
		prodInfo.getQuantity();
		boolean flag=shopcart.ShoppingCartProduct(hm.get("name"),prodInfo.getQuantity(),hm.get("Ex Price"),prodInfo.gettotalPrice());
		Assert.assertTrue(flag,Error.INVALID_PRODUCT_DATA);
		
	}
	
	@Test(priority=2)
	public void verifyCartTableTest()
	{	
		prodInfo.selectQuantity("2");
		prodInfo.addToCart();
		Map<String,String> hm1 = prodInfo.collectCartData();
		ShoppingCart shopcart = prodInfo.navigateToViewCart();
		Map<String,String> hm2 = shopcart.footerCartData();
		Assert.assertEquals(hm1, hm2,Error.INVALID_CART_DATA);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		shopcart.deleteCart();
		driver.close();
	}
	

}
