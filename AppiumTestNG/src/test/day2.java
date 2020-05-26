package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class day2 {

	@Test(groups="SmokeTest")
	public void pload()
	{
		System.out.println("Good");
	}
	
	@BeforeTest
	public void prerequisite()
	{
		System.out.println("Do this before any test is run");
	}
}
