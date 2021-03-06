package Practise.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

// This class is not really required all test classes should really use the 'base2' class.
public class BaseGeneralStoreApp {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	public AppiumDriverLocalService startServer()
	{
		boolean isServerRunning = checkIfServerIsRunning(4723);
		
		// if server is not running then start server
		if(!isServerRunning)
		{
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		
		return service;

	}
	
	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
	{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Practise\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		System.out.println((String) prop.get("device"));
		
		String connectionToServerLink = "http://127.0.0.1:4723/wd/hub";
	
		// src = AppiumFramework/src/
		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		String device = (String) prop.get("device");	
		if(device.contains("emulator-5554"))
		{
			System.out.println("Entered startEmulator block");
			startEmulator();
		}
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);	
		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		// Make a connection to the Appium server
		driver = new AndroidDriver<>(new URL(connectionToServerLink), capabilities);

		return driver;
	}
	
	public static void getScreenShot(String testcaseName) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,  new File(System.getProperty("user.dir") + "\\" + testcaseName + ".png"));
	}
	
	public static boolean checkIfServerIsRunning(int port)
	{
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If code comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

}


