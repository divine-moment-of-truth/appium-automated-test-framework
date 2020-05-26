package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Practise.AppiumFramework.BaseGeneralStoreApp;

public class Listeners implements ITestListener {

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// Take a screen shot
		String testcaseName = result.getName();
		try {
			BaseGeneralStoreApp.getScreenShot(testcaseName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
}
