package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected WebDriver driver;
    protected static final int DEFAULTWAITTIME = 10; // You can adjust this as needed
    private Select select;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void sendKeys(WebElement element, String input) {
        element.sendKeys(input);
    }

    public String getValueFromDropdown(WebElement element) {
        select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    public String getTextFromDropdown(WebElement element) {
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    public void waitImplicitly() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULTWAITTIME));
    }
}
