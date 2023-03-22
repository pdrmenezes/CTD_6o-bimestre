import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public static void main (String[] args) throws Exception{
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.mercadolivre.com.br");
        driver.manage().window().maximize();

        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"cb1-edit\"]"));
        searchBox.sendKeys("iphone 13");

        WebElement searchButton = driver.findElement(By.xpath("/html/body/header/div/form/button"));
        searchButton.click();

        WebElement firstProduct = driver.findElement(By.xpath("//*[@id=\"root-app\"]/div/div[2]/section/ol/li[1]/div/div/div[2]/div[1]/a[1]"));
        firstProduct.click();

        WebElement storage = driver.findElement(By.xpath("//*[@id=\"ui-pdp-main-container\"]/div[1]/div/div[1]/div[2]/div[3]/div[1]/div/a[2]"));
        storage.click();

        Thread.sleep(1500);

        WebElement color = driver.findElement(By.xpath("//*[@id=\"ui-pdp-main-container\"]/div[1]/div/div[1]/div[2]/div[3]/div[2]/div/a[2]"));
        color.click();

        Thread.sleep(1500);

        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"buybox-form\"]/div[5]/div/button[2]"));
        addToCartButton.click();
    }
}