package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    public static WebDriver driver;
    protected static final String URL = "http://fe.deitech.online/";

    public BasePage(){
        this.driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void openApp(){
        getDriver().get(URL);
        getDriver().manage().window().maximize();
    }

    public WebElement getWebElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println("element not found: " + e);
        }
        return element;
    }

    public void quitDriver(){
        getDriver().quit();
    }

}
