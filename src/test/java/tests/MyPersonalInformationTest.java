/**
 * 
 */
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MyPersonalInformationTest extends TestBase {

	private static final Logger log = LoggerFactory.getLogger(MyPersonalInformationTest.class);
	LoginPage loginpage;
	MyPersonalInformationPage myPersonalInfo;
	HashMap<String, String> testData;
	SoftAssert softAssertion;
	
	
	@BeforeMethod
	public void launchApp() throws FileNotFoundException, IOException {
		log.info("Launching browser");
		driver = getDriver(BROWSER_NAME);
		driver.get(URL);
		driver.manage().window().maximize();
		// Log4j2 does not require explicit configuration in code; ensure log4j2.xml is in the classpath
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
