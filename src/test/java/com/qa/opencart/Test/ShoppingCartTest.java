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
		//cp.logInUsingdp();
		accpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyProduct()
	{
		sp=accpage.doSearch("iphone");
		prodInfo=sp.selectProductFromResult("iPhone");
		Map<String,String> hm=prodInfo.getProductInformation();
		prodInfo.selectQuantity("2");
		prodInfo.addToCart();
		String totalprice = "$"+prodInfo.gettotalPrice("2");
		shopcart=prodInfo.navigateToViewCart();
		boolean flag=shopcart.ShoppingCartProduct(hm.get("name"),"2","$"+hm.get("Ex Price"),totalprice);
		shopcart.deleteCart();
		Assert.assertTrue(flag,Error.INVALID_PRODUCT_DATA);
	}
	
	@Test(priority=2,enabled=true)
	public void verifyCartTableTest()
	{
		sp=accpage.doSearch("iphone");
		prodInfo=sp.selectProductFromResult("iPhone");
		prodInfo.selectQuantity("2");
		prodInfo.addToCart();
		Map<String,String> hm1 = prodInfo.collectCartData();
		ShoppingCart shopcart = prodInfo.navigateToViewCart();
		Map<String,String> hm2 = shopcart.footerCartData();
		shopcart.deleteCart();
		Assert.assertEquals(hm1, hm2,Error.INVALID_CART_DATA);
		
	}
	
	@Test(priority=3)
	public void verifyWishList()
	{
		wh=prodInfo.addTowishList();
		boolean flg=wh.verifyProductDetails();
		Assert.assertTrue(flg);
	}
	
	@AfterMethod
	public void BacktoHome()
	{
		cp.gotoMyAccount();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	

}
