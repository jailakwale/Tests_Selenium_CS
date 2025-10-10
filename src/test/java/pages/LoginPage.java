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


/**
 * Description: Contains all the page webelements and methods
 * @author Jai
 *
 */
public class LoginPage extends BasePage {

	@FindBy(xpath = Locators.ACCCEPT_COOKIES)
	public WebElement acceptCookiesbutton;

	@FindBy(xpath = Locators.LOGIN_LINK)
	public WebElement loginLink;

	@FindBy(css = Locators.EMAIL_ADDRESS_INPUT)
	public WebElement emailAddressInput;

	@FindBy(css = Locators.CONTINUE_BUTTON)
	public WebElement continueButton;


	@FindBy(id = Locators.ACCOUNTS_TABLE)
	public WebElement accountsTable;	

	@FindBy(name = Locators.USERNAME)
	public WebElement usernameInput;	

	@FindBy(name = Locators.PASSWORD)
	public WebElement passwordInput;	

	@FindBy(css = Locators.LOGIN_BTN)
	public WebElement loginBtn;	
	
	
	
	/** 
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded()
	{ 
		
		waitImplicitly();
		return true;
	}
	
	/**
	 * Assigning driver and initializing webelements of this page 	  
	 * @param email
	 * @param password
	 *  
	 */	
	public void login(String username, String password) throws InterruptedException {
		
		waitImplicitly();
		sendKeys(usernameInput, username);
		sendKeys(passwordInput, password);
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.elementToBeClickable(loginBtn));
		clickElement(loginBtn);
		waitImplicitly();

	}
	
	
	
}
