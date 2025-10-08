package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JsplaceholderPage extends BasePage {
	
	

	@FindBy(xpath = Locators.placeholder_data)
	public WebElement placeholderData;
	
	/**
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public JsplaceholderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	/** 
	 * Description: get the data from the jsplaceholder webelement 	 
	 *   
	 */	
	public String getJsplaceholderData() {
		
		System.out.println("##############################################");
		String text = placeholderData.getText();
		//System.out.println(text);		
		
		return text;	

	}

}
