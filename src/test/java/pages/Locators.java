/**
 * 
 */
package pages;

/**
 * @author Jai
 *
 */
public class Locators {
	
	/* LOGIN PAGE WEBELEMENTS */
	
	//Few locators below in the comments are written just to show the variations in locators 
	public static final String ACCCEPT_COOKIES = "//*[@id='_evidon-accept-button']";	
	public static final String LOGIN_LINK = "//span[text()='Log in']";
	//div[@id='root']//following::span[text()='Log in']       
	//span[normalize-space()='Log in']	
	public static final String EMAIL_ADDRESS_INPUT = "#identification_email";
	//input[@id='identification_email']
	public static final String CONTINUE_BUTTON = ".css-4nwwco.e1kntgv41";
	//span[normalize-space()='Continue']
	
	//input[@id='password']
	public static final String ACCOUNTS_TABLE = "#accountTable";

	public static final String USERNAME= "username";

	public static final String PASSWORD = "password";

	public static final String LOGIN_BTN = "input[type='submit']";

	
	
	/* MY PERSONAL INFORMATION PAGE WEBELEMENTS */
	
	public static final String MY_PERSONAL_INFO_LINK = "//button[@aria-controls='user-space-user-information']";
	public static final String MALE_RADIOBUTTON = "//span[contains(text(),'Male')]";
	public static final String FIRSTNAME_INPUT = "//input[@name='firstName']";
	public static final String LASTNAME_INPUT = "//input[@name='lastName']";
	public static final String COUNTRYCODE_DROPDOWN = "//select[@id='PHONE_CODE_FIELD']";
	public static final String PHONENUMBER_INPUT = "//input[@name='phoneNumber.nationalNumber']";
	public static final String DOB_DAY_INPUT = "//input[@placeholder='Day']";
	public static final String DOB_MONTH_DROPDOWN = "//select[@name='birthDate.month']";
	public static final String DOB_YEAR_INPUT = "//input[@placeholder='Year']";
	public static final String UPDATE_BUTTON = "//span[normalize-space()='Update']";	
	
	/* POKEMON API PAGE WEBELEMENTS */
	public static final String POKEMON_URLS = "//pre";
	
	
	public static final String placeholder_data = "//pre";
	

}
