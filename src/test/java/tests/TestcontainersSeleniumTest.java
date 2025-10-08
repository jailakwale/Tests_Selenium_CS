package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.Test;
import org.testcontainers.utility.DockerImageName;

public class TestcontainersSeleniumTest {

    @Test
    public void testWithTestcontainers() {
        DockerImageName chromeImage = DockerImageName.parse("seleniarm/standalone-chromium:latest").asCompatibleSubstituteFor("selenium/standalone-chrome");
        BrowserWebDriverContainer<?> chrome = new BrowserWebDriverContainer<>(chromeImage)
                .withCapabilities(new ChromeOptions());
        try {
            chrome.start();
            WebDriver driver = chrome.getWebDriver();
            driver.get("https://www.thefork.com/");
            // Add your test code here
        } finally {
            chrome.close();
        }
    }
}
