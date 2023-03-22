import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver2 {
    public static void main (String[] args) throws Exception {
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.pdrmenezes.com");
        Thread.sleep(300);
        driver.manage().window().maximize();

        WebElement projectsTab = driver.findElement(By.xpath("//*[@id=\"comp-kloyz55t0\"]/a"));
        projectsTab.click();

        WebElement projectCategory = driver.findElement(By.xpath("//*[@id=\"comp-koqn6yqn\"]/h2/span/span[2]/a"));
        projectCategory.click();

        Thread.sleep(500);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(500);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(500);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement backToTop = driver.findElement(By.xpath("//*[@id=\"comp-khvud67n\"]/a"));
        backToTop.click();
        Thread.sleep(500);

        WebElement contactTab = driver.findElement(By.xpath("//*[@id=\"comp-kloyz55t1\"]/a"));
        contactTab.click();
        Thread.sleep(500);

        WebElement hireMe = driver.findElement(By.xpath("//*[@id=\"comp-koqlbsoq\"]/p/span/a"));
        hireMe.click();
    }
}
