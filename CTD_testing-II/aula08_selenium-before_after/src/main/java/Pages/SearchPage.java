package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {
    protected static final String searchInputXpath = "//*[@id=\"ciudad\"]";
    protected static final String searchButtonXpath = "//*[@id=\"btn-buscador\"]";
    protected static final String locationNameXpath = "//*[@id=\"20\"]/div[2]/div/div[1]/h3";

    public void searchByLocation(String location) throws InterruptedException{
        WebElement searchInput = getWebElement(By.xpath(searchInputXpath));
        searchInput.clear();
        searchInput.sendKeys(location);
        Thread.sleep(1500);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void pressSearchButton(){
        WebElement searchButton = getWebElement(By.xpath(searchButtonXpath));
        searchButton.click();
    }

    public String getLocation(){
        WebElement locationName = getWebElement(By.xpath(locationNameXpath));
        return locationName.getText();
    }
}
