/**
 * 
 */
package utility;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Description: Parent class of Page classes that has actions needed to perform
 * on webelements
 *  
 * @author Jai
 *
 */
public class GenericMethods extends TestBase {

	Select select;
	
	/*
	 * Description: Clicks on an element
	 * @param link - webelement locator
	 */
	public void clickElement(WebElement link) {

		link.click();
	}
	
	
	/*
	 * Description: Inputs string into the box	
	 * @param textbox
	 * @param input - webelement
	 */
	public void sendKeys(WebElement textbox, String input) {

		textbox.sendKeys(input);
	}
	
	/*
	 * Description: gets first selected value from dropdown 
	 * @param element - webelement
	 */
	public String getValueFromDropdown(WebElement element) {

		select = new Select(element);
		return select.getFirstSelectedOption().getAttribute("value");
	}
	
	
	/*
	 * Description: gets first selected value from dropdown as text	
	 * @param element - webelement
	 */
	public String getTextFromDropdown(WebElement element) {

		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	
	/*
	 * Description: waits implicitly for default time set in config before throwing exception
	 * 
	 */
	public void waitImplicitly() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULTWAITTIME));		
	}

}
