/**
 * 
 */
package tests;

import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.HashMap;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MyPersonalInformationPage;
import utility.TestBase;

/**
 * @author Jai
 *
 */
public class MyPersonalInformation extends TestBase {

	private static final Logger log = org.apache.log4j.LogManager.getLogger(MyPersonalInformation.class);
	LoginPage loginpage;
	MyPersonalInformationPage myPersonalInfo;
	HashMap<String, String> testData;
	SoftAssert softAssertion;
	
	
	@BeforeMethod
	public void launchApp() throws FileNotFoundException, IOException {
		log.info("Launching browser");
		driver = getDriver(browserName);
		driver.get(URL);
		driver.manage().window().maximize();
		BasicConfigurator.configure(); //Configures the logs
	}

	@Test
	public void validateMyPersonalInformation() throws InterruptedException, IOException, ParseException {

		loginpage = new LoginPage(driver);
		myPersonalInfo = new MyPersonalInformationPage(driver);
		softAssertion = new SoftAssert();
		//Get username and password to login
		String username = readDataFromJson("commonData", "username");
		String password = readDataFromJson("commonData", "password");
		//Extract testdata from data file
		String testCaseName = "validateMyPersonalInformation";
		testData = extractDataFromJson(testCaseName);
		//Login to the web app
		loginpage.login(username, password);
		//Navigate to My Personal Info page and assert all the field values
		myPersonalInfo.clickMyPersonalInfoLink();
		softAssertion.assertEquals(testData.get("firstname"), myPersonalInfo.getFirstname());
		log.info("First name validated successfully");		
		softAssertion.assertEquals(testData.get("lastname"), myPersonalInfo.getLastname());
		log.info("Last name validated successfully");		
		softAssertion.assertEquals(testData.get("countrycode"), myPersonalInfo.getCountrycode());
		log.info("Country Code validated successfully");
		softAssertion.assertEquals(testData.get("phonenumber"), myPersonalInfo.getPhonenumber());
		log.info("Phone number validated successfully");
		softAssertion.assertEquals(testData.get("dayDOB"), myPersonalInfo.getDayFromDOB());
		log.info("Day from DOB validated successfully");
		softAssertion.assertEquals(testData.get("monthDOB"), myPersonalInfo.getMonthFromDOB());
		log.info("Month from DOB validated successfully");
		softAssertion.assertEquals(testData.get("yearDOB"), myPersonalInfo.getYearFromDOB());
		log.info("Year from DOB validated successfully");
		//Assert all to check the failure points
		softAssertion.assertAll();
	}

	@AfterMethod
	public void teardown() {
		log.info("Test Execution Complete");
		driver.quit();
	}

}
