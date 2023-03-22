package FrontEnd.Pages;

import FrontEnd.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BalancePage extends BasePage {
  protected static final String totalBalanceXpath = "//*[@id=\"textBalanceAvailable\"]";
  protected static final String transactionAmountXpath = "//*[@id=\"textTransferValue\"]";
  protected static final String transactionDescriptionXpath = "//*[@id=\"textDescription\"]";
  protected static final String backButtonXpath = "//*[@id=\"btnBack\"]";

  public String getTotalBalanceText() {
    WebElement totalBalance = getWebElement(By.xpath(totalBalanceXpath));
    return totalBalance.getText();
  }

  public String getTransactionAmount() {
    WebElement transactionAmount = getWebElement(By.xpath(transactionAmountXpath));
    return transactionAmount.getText();
  }

  public String getTransactionDescription() {
    WebElement transactionDescription = getWebElement(By.xpath(transactionDescriptionXpath));
    return transactionDescription.getText();
  }

  public void returnToLoggedPage() {
    WebElement backButton = getWebElement(By.xpath(backButtonXpath));
    backButton.click();
  }
}
