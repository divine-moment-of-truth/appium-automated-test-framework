package Practise.AppiumFramework;

import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.GeneralStore_CheckoutPage;
import PageObject.GeneralStore_FormPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.FileInputStream;
import java.io.IOException;

// Test Case Class adds 2 different shoes to the cart and then checks that the total purchase amount is the sum of the two selected shoes
public class Ecommerce_tc_3 extends BaseGeneralStoreApp {

	@Test
	public void totalValidation() throws IOException, InterruptedException
	{		
		service = startServer();
		// service.start();
		
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Instantiate Form Page object
		GeneralStore_FormPage formPage = new GeneralStore_FormPage(driver);
		
		// Instantiate Checkout page object
		GeneralStore_CheckoutPage checkoutPage = new GeneralStore_CheckoutPage(driver);
		
		// enter name into 'Your Name' input box
		// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Andy");
		formPage.getNameField().sendKeys("Hello");
		
		// hide the keyboard from appearing on the app
		driver.hideKeyboard();
		
		// select 'Female' radio button
		// driver.findElement(By.xpath("//*[@text='Female']")).click();
		formPage.getfemaleOption().click();
		
		// click the country drop down
		// driver.findElement(By.id("android:id/text1")).click();
		formPage.getCountrySelection().click();
		
		// Scroll down to 'Argentina'
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		Utilities utilities = new Utilities(driver);
		utilities.ScrollText("Argentina");
		
		// click 'Argentina'
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		
		// click 'Let's Shop' button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		// Click first product on screen'(ADD TO CART' button 
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		// Click first product on screen'(ADD TO CART' button - When the above item was clicked the 'ADD TO CART' button becomes disabled, therefore
		// changing the index so you need to using 'get(0)' again below
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		
		// Click the shopping cart icon to go to checkout
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		// Add a wait here because the ids in the next step also exist on the next page (the shopping cart page) which causes Appium to get confused
		Thread.sleep(4000);
		
		// ************************** CHECKOUT PAGE ***************************
		// Get number of products
		int numOfProducts = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		
		// Create a variable for summing the total value of products
		double sumOfProducts = 0;
		
		for (int i=0; i < numOfProducts; i++)
		{
			// String amount = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			String amount = checkoutPage.getProductList().get(i).getText();
			double amountValue = getAmount(amount);
			
			sumOfProducts = sumOfProducts + amountValue;
		}
		
		// get price of each shoe in the shopping cart
		// String firstShoeValue = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		// String secondShoeValue = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		
		// Get the numeric part of the price
		// firstShoeValue = firstShoeValue.substring(1);
		// secondShoeValue = secondShoeValue.substring(1);
		
		// convert string values into double types
		// double firstShoeNumericValue = Double.parseDouble(firstShoeValue);
		// double secondShoeNumericValue = Double.parseDouble(secondShoeValue);
		
		// double firstShoeNumericValue = getAmount(firstShoeValue);
		// double secondShoeNumericValue = getAmount(secondShoeValue);
		
		// sum products
		// double sumOfProducts = firstShoeNumericValue + secondShoeNumericValue;
		System.out.println("Sum of products = " + sumOfProducts);
		
		// Get 'Total Purchase Amount'
		// String totalPurchaseAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		String totalPurchaseAmount = checkoutPage.getTotalAmount().getText();
		System.out.println(totalPurchaseAmount);
				
		totalPurchaseAmount = totalPurchaseAmount.substring(2);
		double totalPurchaseAmountNumeric = Double.parseDouble(totalPurchaseAmount); 
		System.out.println("Total price on screen = " + sumOfProducts);
		
		Assert.assertEquals(sumOfProducts, totalPurchaseAmountNumeric);
		
		// Click the 'Send me e-mails...' checkbox
		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(tapOptions().withElement(element(checkbox))).perform();
		
		// Click and hold 'Please read our terms of conditions' text
		WebElement termsConditionsElement = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		touchAction.longPress(longPressOptions().withElement(element(termsConditionsElement)).withDuration(ofSeconds(2))).release().perform();
		
		// Click on the 'Close' button
		driver.findElement(By.id("android:id/button1")).click();
				
		// Click 'Visit to the website to complete purchase' button
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		service.stop();
		
	}
	
	@BeforeTest
	public void killAllProcesses() throws IOException, InterruptedException
	{
		// this line runs the command in command prompt to kill all processes
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}
	
	public static double getAmount(String value)
	{
		// Get the numeric part of the price
		value = value.substring(1);
		
		// convert string values into double types
		double valueConvertedToNumericValue = Double.parseDouble(value);
		
		return valueConvertedToNumericValue;
		
	}
	
		
}
