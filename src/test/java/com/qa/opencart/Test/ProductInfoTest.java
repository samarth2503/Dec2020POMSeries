package com.qa.opencart.Test;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void AccSetup()
	{
		accpage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoDataTest()
	{
		sp=accpage.doSearch("macbook");
		prodInfo=sp.selectProductFromResult("iPhone");
		Map<String,String> productInfo = prodInfo.getProductInformation();
		softassert.assertTrue(productInfo.get("name").equals("iPhone"));
		softassert.assertTrue(productInfo.get("price").equals("101.00"));
		softassert.assertTrue(productInfo.get("Brand").equals("Apple"));
		softassert.assertAll();
		
		//productInfo.equals(o);
	}
	

	
	
	
}
