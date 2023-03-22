package FrontEnd.Pages;

import FrontEnd.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TransferPage extends BasePage {
  protected static final String accountNumberInputXpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[1]/input";
  protected static final String accountDigitInputXpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[2]/input";
  protected static final String transferAmountInputXpath = "//*[@id=\"__next\"]/div/div[3]/form/div[2]/input";
  protected static final String descriptionInputXpath = "//*[@id=\"__next\"]/div/div[3]/form/div[3]/input";
  protected static final String transferButtonXpath = "//*[@id=\"__next\"]/div/div[3]/form/button";
  protected static final String warningToasterXpath = "//*[@id=\"modalText\"]";


  public void inputTransferData(String accountNumber, String accountDigit, String transferAmount, String description) {
    WebElement accountNumberInput = getWebElement(By.xpath(accountNumberInputXpath));
    accountNumberInput.clear();
    accountNumberInput.sendKeys(accountNumber);

    WebElement accountDigitInput = getWebElement(By.xpath(accountDigitInputXpath));
    accountDigitInput.clear();
    accountDigitInput.sendKeys(accountDigit);

    WebElement transferAmountInput = getWebElement(By.xpath(transferAmountInputXpath));
    transferAmountInput.clear();
    transferAmountInput.sendKeys(transferAmount);

    WebElement descriptionInput = getWebElement(By.xpath(descriptionInputXpath));
    descriptionInput.clear();
    descriptionInput.sendKeys(description);
  }

  public void inputTransferAmount(String transferAmount) {
    WebElement transferAmountInput = getWebElement(By.xpath(transferAmountInputXpath));
    transferAmountInput.clear();
    transferAmountInput.sendKeys(transferAmount);
  }

  public void pressTransferButton() {
    WebElement transferButton = getWebElement(By.xpath(transferButtonXpath));
    transferButton.click();
  }

  public String getWarningToasterMessage() {
    WebElement warningToasterMessage = getWebElement(By.xpath(warningToasterXpath));
    return warningToasterMessage.getText();
    // Conta inválida ou inexistente || Transferência realizada com sucesso
  }
}
