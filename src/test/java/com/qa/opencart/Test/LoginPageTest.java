package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Story("Us101: design login page for demo cart application")
@Epic("Epic-100: design Login page feature")
public class LoginPageTest extends BaseTest {
	
	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void titleTest()
	{
		Assert.assertEquals(lp.getLoginPageTitle(),Constants.login_page_title);
	}
	
	@Description("Login test with correct credentails")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void LoginTest()
	{
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(driver.getTitle(),Constants.Account_page_title);
	}
	
	@Description("To verify wheather Forgot Password link is present on Login Page")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(lp.forgotPasswordLink());
	}
	
}
