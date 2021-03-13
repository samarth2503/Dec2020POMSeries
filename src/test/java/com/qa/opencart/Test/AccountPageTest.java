package com.qa.opencart.Test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.util.Constants;
import com.qa.opencart.util.Error;

public class AccountPageTest extends BaseTest{
	
	SoftAssert softassert = new SoftAssert();
	
	@BeforeClass
	public void Accsetup()
	{
		lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void AccountpagetitleTest()
	{
		String title = accpage.getPageTitle();
		Assert.assertEquals(title, Constants.Account_page_title,Error.ACC_PAGE_MISMATCHED_ERROR);
	}
	
	@Test(priority=2)
	public void AccountPageHeaderCountTest()
	{
		int page_count=accpage.getAccountpageHeaderCount();
		Assert.assertEquals(page_count, Constants.Accountpageheadercount);
	}
	
	@Test(priority=3)
	public void verifygetAccountpageHeaderText()
	{
		List<String> actual = accpage.getAccountpageHeaderText();
		List<String> exp = Constants.getHeader();
		
		Assert.assertEquals(actual, exp);
	}
	
	@Test
	public void accPageLogoTest()
	{
		Assert.assertTrue(accpage.isLogoDisplayed(),Error.LOGO_NOT_AVAILABLE_ERROR);
	}
	
	@Test
	public void doSerachTest()
	{
		sp=accpage.doSearch("macbook");
		Assert.assertTrue(sp.getProductcount()>0,Error.SEARCH_PRODUCT_ERROR);
	}
	
	@Test
	public void selectProductTest()
	{
		sp=accpage.doSearch("macbook");
		prodInfo=sp.selectProductFromResult("Macbook Pro");
		softassert.assertEquals(prodInfo.getProductInfoHeader(),"Macbook");
	}
	
}
