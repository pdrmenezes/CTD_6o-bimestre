package FrontEnd.Pages;

import FrontEnd.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
  protected static final String homeRegisterButtonXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/form/div[3]/button[2]";
  protected static final String emailInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/input";
  protected static final String nameInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[3]/input";
  protected static final String passwordInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[4]/div/input";
  protected static final String confirmPasswordInputXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[5]/div/input";
  protected static final String toggleCreateAccountWithBalanceXpath = "//*[@id=\"toggleAddBalance\"]";
  protected static final String registerButtonXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/button";
  protected static final String inputFieldWarningMessageXpath = "//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div[2]/p";
  protected static final String warningToasterId = "modalText";
  protected static final String closeToasterButtonXpath = "//*[@id=\"btnCloseModal\"]";


  public void goToRegisterForm() {
    WebElement registerButton = getWebElement(By.xpath(homeRegisterButtonXpath));
    registerButton.click();
  }

  public void inputRegisterData(String email, String name, String password, String confirmPassword) {
    WebElement emailInput = getWebElement(By.xpath(emailInputXpath));
    emailInput.clear();
    emailInput.sendKeys(email);

    WebElement nameInput = getWebElement(By.xpath(nameInputXpath));
    nameInput.clear();
    nameInput.sendKeys(name);

    WebElement passwordInput = getWebElement(By.xpath(passwordInputXpath));
    passwordInput.clear();
    passwordInput.sendKeys(password);

    WebElement confirmPasswordInput = getWebElement(By.xpath(confirmPasswordInputXpath));
    confirmPasswordInput.clear();
    confirmPasswordInput.sendKeys(confirmPassword);
  }

  public void toggleCreateAccountWithBalance() {
    WebElement toggle = getWebElement(By.xpath(toggleCreateAccountWithBalanceXpath));
    toggle.click();
  }

  public void pressRegisterButton() {
    WebElement registerButton = getWebElement(By.xpath(registerButtonXpath));
    registerButton.click();
  }

  public String getFieldWarningMessage() {
    WebElement fieldWarningMessage = getWebElement(By.xpath(inputFieldWarningMessageXpath));
    return fieldWarningMessage.getText();
    // É campo obrigatório
    // Formato inválido
  }

  public String getWarningToasterMessage() {
    WebElement warningToasterMessage = getWebElement(By.id(warningToasterId));
    return warningToasterMessage.getText();
    // Nome não pode ser vazio.
    // As senhas não são iguais.
  }

  public void register(String email, String name, String password, String confirmPassword) {
    inputRegisterData(email, name, password, confirmPassword);
    toggleCreateAccountWithBalance();
    pressRegisterButton();
  }

  public void closeToaster() {
    WebElement closeToasterButton = getWebElement(By.xpath(closeToasterButtonXpath));
    closeToasterButton.click();
  }
}
