package FrontEnd.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
  protected static final String URL = "https://bugbank.netlify.app/";
  public static WebDriver driver;

  public BasePage() {
  }

  public WebDriver getDriver() {
    return driver = new ChromeDriver();
  }

  public void openApp() {
    driver = getDriver();
    driver.get(URL);
    driver.manage().window().maximize();
  }

  public void closeDriver() {
    driver.close();
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


}
