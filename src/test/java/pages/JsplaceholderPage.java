package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.GenericMethods;

public class JsplaceholderPage extends GenericMethods {
	
	

	@FindBy(xpath = Locators.placeholder_data)
	public WebElement placeholderData;
	
	/**
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public JsplaceholderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	/** 
	 * Description: get the data from the pokemon webelement 	 
	 *   
	 */	
	public String getJsplaceholderData() {
		
		System.out.println("##############################################");
		String text = placeholderData.getText();
		System.out.println(text);		
		
		return text;	

	}

}

