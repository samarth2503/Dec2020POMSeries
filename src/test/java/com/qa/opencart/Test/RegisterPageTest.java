package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.util.ExcelUtil;
import com.qa.opencart.util.Error;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void RegisterpageSetup()
	{
		register=lp.navigateToRegsiterPage();
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = ExcelUtil.getData("Register");
		return data;
	}
	
	@Test(dataProvider="getData")
	public void userRegisterTest(String firstname,String lastname,String email,String telephone,String password,String cnfpassword,String privacyPolicy,String subscribe)
	{
		Assert.assertTrue(register.registerUser(firstname, lastname, email, telephone, password, cnfpassword, privacyPolicy, subscribe),Error.REGISTRATION_NOT_SUCCESS_MSG);
	}


}
