package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
	
	public static final String login_page_title = "Account Login";
	public static final String Account_page_title = "My Account";
	
	public static final ArrayList<String> getHeader()
	{
		return new ArrayList<String> (Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));
	}
	
	public static final int Accountpageheadercount=4;
	
	public static final String ACCOUNT_CREATION_SUCCESS_MSG="Your Account Has Been Created!";
}
