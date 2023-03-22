package FrontEnd.Pages;

import FrontEnd.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
  protected static final String emailInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[1]/input";
  protected static final String passwordInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[2]/div/input";
  protected static final String accessButtonXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[1]";
  protected static final String registerButtonXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]";
  protected static final String inputFieldWarningMessageXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[1]/p";
  protected static final String failedLoginTextXpath = "//*[@id=\"modalText\"]";

  public void inputFullLoginData(String email, String password) {
    WebElement emailInput = getWebElement(By.xpath(emailInputXpath));
    emailInput.clear();
    emailInput.sendKeys(email);

    WebElement passwordInput = getWebElement(By.xpath(passwordInputXpath));
    passwordInput.clear();
    passwordInput.sendKeys(password);
  }

  public void inputEmail(String email) {
    WebElement emailInput = getWebElement(By.xpath(emailInputXpath));
    emailInput.clear();
    emailInput.sendKeys(email);
  }

  public void inputPassword(String password) {
    WebElement passwordInput = getWebElement(By.xpath(passwordInputXpath));
    passwordInput.clear();
    passwordInput.sendKeys(password);
  }

  public void inputIncorretEmailFormat(String incorrectEmailFormat) {
    WebElement emaildInput = getWebElement(By.xpath(emailInputXpath));
    emaildInput.clear();
    emaildInput.sendKeys(incorrectEmailFormat);
  }

  public void pressAccessButton() {
    WebElement accessButton = getWebElement(By.xpath(accessButtonXpath));
    accessButton.click();
  }

  public String getFieldWarningMessage() {
    WebElement fieldWarningMessage = getWebElement(By.xpath(inputFieldWarningMessageXpath));
    return fieldWarningMessage.getText();
    // É campo obrigatório
    // Formato inválido
  }

  public String getFailedLoginMessage() {
    WebElement failedLoginMessage = getWebElement(By.xpath(failedLoginTextXpath));
    return failedLoginMessage.getText();
    // Usuário ou senha inválido. Tente novamente ou verifique suas informações!
  }

  public void goToRegisterPage() {
    WebElement registerButton = getWebElement(By.xpath(registerButtonXpath));
  }
}
