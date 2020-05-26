package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class day3 {

	@BeforeClass
	public void BeforeClass()
	{
		System.out.println("Before executing any methods in the class!!");
	}
	
	@AfterClass
	public void AfterClass()
	{
		System.out.println("After executing all methods in the class!!");
	}
	
	@Test(enabled=false)
	public void WebLoginCarLoan()
	{
		// Selenium
		System.out.println("Web Login Car Load. 'enabled=false' means that this test will not be run.");
	}
	
	@Test(groups="SmokeTest")
	public void MobileLoginCarLoan()
	{
		// Appium
		System.out.println("Mobile Login Car Load");
	}
	
	@Test(timeOut=400)
	public void MobileSignupCarLoan()
	{
		// Appium
		System.out.println("Mobile signup Car Load. TimeOut=4000 means that this test will fail if it has not completed withon 4 seconds.");
	}
	
	@BeforeMethod
	public void BeforeEveryMethod()
	{
		System.out.println("Run before every method in day 3 class. Only for methods within this test class.");
	}
	
	@AfterMethod
	public void AfterEveryMethod()
	{
		System.out.println("Run after every method in the day 3 class. Only for methods with this test class.");
	}
	
	@BeforeSuite
	public void BeforeSuite()
	{
		System.out.println("Running Before Suite!!!");
	}
	
	@AfterSuite
	public void AfterSuite()
	{
		System.out.println("Running After Suite!!!");
	}
	
	@Parameters({"URL","APIKey/username"})
	@Test
	public void MobileLogoutCarLoan(String urlName, String APIkey)
	{
		// Appium
		System.out.println("Mobile Logout Car Load. @Parameters({URL}) takes the value from the 'testng.xml' file.");
		System.out.println("*********** URL Name is - " + urlName + "************");		
		System.out.println("*********** Key is - " + APIkey + "************");	
	}
	
	@Test
	public void TestCarLoan1()
	{
		// Appium
		System.out.println("Test - Method1.");
	}
	
	@Test
	public void TestCarLoan2()
	{
		// Appium
		System.out.println("Test - Method2.");
	}
	
	@Test(dependsOnMethods={"TestCarLoan1","TestCarLoan2"})
	//@Test(dependsOnMethods={"MobileSignupCarLoan","MobileLoginCarLoan"})
	public void LoginAPICarLoan()
	{
		// Rest API Automation
		System.out.println("API Login Car Load. Using 'dependsOnMethods' means that method 'MobileSignupCarLoan' and 'MobileLoginCarLoan' has to run first before running this test");
	}
	
	@Test(dataProvider="getData")
	public void dataProviderTest(String username, String password)
	{
		System.out.println("Username - " + username);
		System.out.println("Password - " + password);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		// 1st - username password - good credit history
		// 2nd - username password - no credit history
		// 3rd - username password - fraudulent credit history
		
		Object[][] data = new Object[3][2];
		
		// 1st
		data[0][0]="firstUsername";
		data[0][1]="firstPassword";
		
		// 2nd
		data[1][0]="secondUsername";
		data[1][1]="secondPassword";
				
		// 3rd
		data[2][0]="thirdUsername";
		data[2][1]="thirdPassword";
		
		return data;
	}
}
