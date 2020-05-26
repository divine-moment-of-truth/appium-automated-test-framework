package Practise.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PageObject.HomePage;
import PageObject.PreferencesPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ApiDemoTest extends Base2 {

	@Test(dataProvider="InputData",dataProviderClass=TestData.class)
	public void apiDemo(String input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		service = startServer();
		// service.start();

		AndroidDriver<AndroidElement> driver = capabilities("apiDemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Declare HomePage page object class
		HomePage homePage = new HomePage(driver);
		
		// Declare Preferences page object class
		PreferencesPage preferencesPage = new PreferencesPage(driver); 
		
		
		// Click 'Preference' option in app menu
		// driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		homePage.PreferencesMenuOption.click();
		
		// Click the 'Preference dependencies' menu option
		// driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		preferencesPage.PreferencesDependencies.click();
		
		// Click on WiFi check box
		driver.findElementById("android:id/checkbox").click();
		
		// Click the 'WiFi settings' option - use [2] because there are two references on the page to
		// 'android.widget.RelativeLayout' and it is the second reference so put [2]
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		
		// Click the Wifi settings input element
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		
		// Click 'OK' button
		// driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		// driver.findElementsByClassName("//android.widget.Button").get(1).click();
		preferencesPage.buttons.get(1).click();
		
		service.stop();
		
	}
	
	@BeforeTest
	public void killAllProcesses() throws IOException, InterruptedException
	{
		// this line runs the command in command prompt to kill all processes
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}

}
