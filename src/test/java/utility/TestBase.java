/**
 * 
 */
package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to initialize driver, read and extract test data and config properties
 * @author Jai
 *
 */
public class TestBase {
	
	public static final String USERDIR = System.getProperty("user.dir");
	public static final String ENV_CONFIG = System.getenv("ENV_CONFIG") != null ? System.getenv("ENV_CONFIG") : "config.properties";
	public static final String CONFIGPROPERTIESFILEPATH = USERDIR + "/src/test/resources/Properties/" + ENV_CONFIG;	
	public static final String TESTDATAFILEPATH = USERDIR + readConfigPropertiesFile("testData.path");	
	public static final int MINWAITTIME = Integer.parseInt(readConfigPropertiesFile("min.waitTime"));
	public static final int DEFAULTWAITTIME = Integer.parseInt(readConfigPropertiesFile("default.waitTime"));
	public static final int MAXWAITTIME = Integer.parseInt(readConfigPropertiesFile("max.waitTime"));
	public static final String BROWSER_NAME = readConfigPropertiesFile("browser.name");
	public static final String URL = readConfigPropertiesFile("url");
	public static final String POKEMON_URL = readConfigPropertiesFile("pokemonurl");
	public static final String JSPLACEHOLDER_URL = readConfigPropertiesFile("jsplaceholderurl");
	
	protected static WebDriver driver = null;
	private static final Logger log = LoggerFactory.getLogger(TestBase.class);
	

	/**
	 * Gets the browser driver
	 * 
	 * @param browserName
	 * @return driver
	 * 
	 */
	public static WebDriver getDriver(String browserName) {
	    if (driver != null) {
	        return driver;
	    }
	    String gridUrl = readConfigPropertiesFile("grid.url");
	    if (gridUrl != null && !gridUrl.isEmpty()) {
	        try {
	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setBrowserName(browserName.toLowerCase());
	            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
	        } catch (IOException | RuntimeException e) {
	            log.error("Failed to connect to Selenium Grid: {}", gridUrl, e);
	            throw new RuntimeException("Failed to connect to Selenium Grid: " + gridUrl, e);
	        }
	    } else {
	        switch (browserName) {
	            case "Firefox":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            case "IE":
	                WebDriverManager.iedriver().setup();
	                driver = new InternetExplorerDriver();
	                break;
	            case "Chrome":
	            default:
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;
	        }
	    }
	    return driver;
	}

	public static void quitDriver() {
	    if (driver != null) {
	        driver.quit();
	        driver = null;
	    }
	}
	
	
	/**
	 * Reads config.properties file
	 * 
	 * @param key
	 * @return value of the key(passed as argument)
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readConfigPropertiesFile(String key) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIGPROPERTIESFILEPATH));
		} catch (FileNotFoundException e) {			
			log.error("File Not found as File path = {}", CONFIGPROPERTIESFILEPATH, e);
			Assert.fail("File Not found as File path = " + CONFIGPROPERTIESFILEPATH, e);
		} catch (IOException e) {		
			log.error("IO Exception as File path = {}", CONFIGPROPERTIESFILEPATH, e);
			Assert.fail("IO Exception as File path = " + CONFIGPROPERTIESFILEPATH, e);
		}
		String value = prop.getProperty(key);
		
		return value;
	}
	
	
	
	/**
	 * Reads test data from testData.JSON file
	 * @param testCaseName Name of the testCase	  
	 * @return test data value as HashMap
	 * @throws IOException
	 * @throws ParseException
	 */
	public static LinkedHashMap<String, String> extractDataFromJson(String testCaseName) throws IOException, ParseException{
		
		LinkedHashMap<String, String>  map = new LinkedHashMap<>();		
		FileInputStream fis= new FileInputStream(TESTDATAFILEPATH);
		InputStreamReader isr= new InputStreamReader(fis);		
		JSONParser jParser = new JSONParser();
		JSONObject jFileObj = (JSONObject) jParser.parse(isr);		
		JSONObject jDataObj = (JSONObject) jFileObj.get(testCaseName);	
		if(testCaseName != null ) {			
			if(null!=jDataObj) {
			   @SuppressWarnings("unchecked")
			   Set<String> keys =  (Set<String>) jDataObj.keySet();
				for(String key : keys) {
					map.put(key, jDataObj.get(key).toString());
					
				}				
			}
		}
		return map;
	}
	
	
	/**
	 * Reads test data form testData.JSON file
	 * @param testCaseName Name of the testCase
	 * @param key Key
	 * @return test data value as String
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String readDataFromJson(String testCaseName, String key) throws IOException, ParseException{
		
		FileInputStream fis= new FileInputStream(TESTDATAFILEPATH);
		InputStreamReader isr= new InputStreamReader(fis);		
		JSONParser jParser = new JSONParser();
		JSONObject jFileObj = (JSONObject) jParser.parse(isr);
		JSONObject jDataObj = (JSONObject) jFileObj.get(testCaseName);
		String value = (String) jDataObj.get(key);
		
		return value;	
	}

	

}
