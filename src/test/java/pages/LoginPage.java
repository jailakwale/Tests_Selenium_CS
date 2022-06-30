/**
 * 
 */
package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.GenericMethods;


/**
 * Description: Contains all the page webelements and methods
 * @author Jai
 *
 */
public class LoginPage extends GenericMethods {

	@FindBy(xpath = Locators.ACCCEPT_COOKIES)
	public WebElement acceptCookiesbutton;

	@FindBy(xpath = Locators.LOGIN_LINK)
	public WebElement loginLink;

	@FindBy(css = Locators.EMAIL_ADDRESS_INPUT)
	public WebElement emailAddressInput;

	@FindBy(css = Locators.CONTINUE_BUTTON)
	public WebElement continueButton;

	@FindBy(css = Locators.PASSWORD_INPUT)
	public WebElement passwordInput;
	
	
	
	/** 
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Assigning driver and initializing webelements of this page 	  
	 * @param email
	 * @param password
	 *  
	 */	
	public void login(String email, String password) throws InterruptedException {
		
		waitImplicitly();
		Thread.sleep(15000); // This is written in order to have some time to manually verify the Robot-recaptcha that cannot be verified by automation
		clickElement(acceptCookiesbutton);
		clickElement(loginLink);
		sendKeys(emailAddressInput, email);
		clickElement(continueButton);
		sendKeys(passwordInput, password);
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.elementToBeClickable(continueButton));
		clickElement(continueButton);

	}
	
	
	
}
