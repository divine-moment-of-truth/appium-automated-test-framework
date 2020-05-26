package PageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	public HomePage(AppiumDriver driver)
	{
		// this - refers to this page only
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement PreferencesMenuOption;
	
	// if path uses 'id' then just write:-
	// @AndroidFindBy(id="//android.widget.TextView[@text='Preference']")
	
	// if path uses 'className' then just write:-
	// @AndroidFindBy(className="//android.widget.TextView[@text='Preference']")
	
	
}
