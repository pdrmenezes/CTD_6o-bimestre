package FrontEnd.Pages;

import FrontEnd.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoggedPage extends BasePage {
  protected static final String welcomeMessageXpath = "///*[@id=\"textName\"]";
  protected static final String totalBalanceXpath = "//*[@id=\"textBalance\"]";
  protected static final String transferButtonXpath = "//*[@id=\"btn-TRANSFERÊNCIA\"]";
  protected static final String balanceButtonXpath = "//*[@id=\"btn-EXTRATO\"]";
  protected static final String logoutButtonXpath = "//*[@id=\"btnExit\"]";

  public String getWelcomeMessageText() {
    WebElement welcomeMessage = getWebElement(By.xpath(welcomeMessageXpath));
    return welcomeMessage.getText();
  }

  public String getTotalBalance() {
    WebElement totalBalance = getWebElement(By.xpath(totalBalanceXpath));
    System.out.println("Total Balance: " + totalBalance);
    return totalBalance.getText();
  }

  public void goToTransferPage() {
    WebElement transferButton = getWebElement(By.xpath(transferButtonXpath));
    transferButton.click();
  }

  public void goToBalancePage() {
    WebElement transferButton = getWebElement(By.xpath(balanceButtonXpath));
    transferButton.click();
  }
}
