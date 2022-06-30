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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.GenericMethods;

/**
 * Description: Contains all the page webelements and methods
 * @author Jai
 *
 */
public class MyPersonalInformationPage extends GenericMethods {

	@FindBy(xpath = Locators.MY_PERSONAL_INFO_LINK)
	public WebElement myPersonalInfoLink;

	@FindBy(xpath = Locators.MALE_RADIOBUTTON)
	public WebElement maleRadiobtn;

	@FindBy(xpath = Locators.FIRSTNAME_INPUT)
	public WebElement firstnameInput;

	@FindBy(xpath = Locators.LASTNAME_INPUT)
	public WebElement lastnameInput;

	@FindBy(xpath = Locators.COUNTRYCODE_DROPDOWN)
	public WebElement countrycodeDropdown;

	@FindBy(xpath = Locators.PHONENUMBER_INPUT)
	public WebElement phonenumberInput;

	@FindBy(xpath = Locators.DOB_DAY_INPUT)
	public WebElement dayDOBinput;

	@FindBy(xpath = Locators.DOB_MONTH_DROPDOWN)
	public WebElement monthDOBinput;

	@FindBy(xpath = Locators.DOB_YEAR_INPUT)
	public WebElement yearDOBinput;

	Select select;
	
	
	/* 
	 * Decription:Assigning driver and initializing webelements of this page 	
	 * @param driver
	 *  
	 */	
	public MyPersonalInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	/* 
	 * Decription:click My Personal Information link 	
	 *   
	 */	
	public void clickMyPersonalInfoLink() {
		
		clickElement(myPersonalInfoLink);
	}
	

	/* 
	 * Decription:get the firstname from the field 	
	 *   
	 */	
	public String getFirstname() {
		new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(firstnameInput));
		return firstnameInput.getAttribute("value");
	}
	
	
	/* 
	 * Decription:get the lastname from the field 	
	 *   
	 */	
	public String getLastname() {

		return lastnameInput.getAttribute("value");
	}

	/* 
	 * Decription:get the country code from the dropdown	
	 *   
	 */	
	public String getCountrycode() {

		return getTextFromDropdown(countrycodeDropdown);
	}
	
	
	/* 
	 * Decription:get the phone number from the field 	
	 *   
	 */	
	public String getPhonenumber() {

		return phonenumberInput.getAttribute("value");
	}
	
	
	/* 
	 * Decription:get the day from the DOB field 	
	 *   
	 */	
	public String getDayFromDOB() {

		return dayDOBinput.getAttribute("value");
	}
	
	/* 
	 * Decription:get the month from the dropdown 	
	 *   
	 */	
	public String getMonthFromDOB() {

		return getValueFromDropdown(monthDOBinput);
	}
	
	/* 
	 * Decription:get the year from the field 	
	 *   
	 */	
	public String getYearFromDOB() {

		return yearDOBinput.getAttribute("value");
	}

}
