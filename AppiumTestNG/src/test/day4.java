package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class day4 {

	@Test
	public void WebLoginHomeLoan()
	{
		// Selenium
		System.out.println("Web Login Home Load");
	}
	
	@Parameters({ "URL" })
	@Test
	public void MobileLoginHomeLoan(String urlName)
	{
		// Appium
		System.out.println("Mobile Login Home Load");
		System.out.println("********* Url is - " + urlName + " *******");
	}
	
	@Test(groups="SmokeTest")
	public void LoginAPIHomeLoan()
	{
		// Rest API Automation
		System.out.println("API Login Home Load");
	}
	
}
