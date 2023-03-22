package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
  protected static final String myAccountBtnXpath = "//*[@id=\"top-links\"]/ul/li[2]/a";
  protected static final String RegisterBtnXpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a";
  protected static final String firstNameInputXpath = "//*[@id=\"input-firstname\"]";
  protected static final String lastNameInputXpath = "//*[@id=\"input-lastname\"]";
  protected static final String emailInputXpath = "//*[@id=\"input-email\"]";
  protected static final String phoneInputXpath = "//*[@id=\"input-telephone\"]";
  protected static final String passwordInputXpath = "//*[@id=\"input-password\"]";
  protected static final String confirmPasswordInputXpath = "//*[@id=\"input-confirm\"]";
  protected static final String noNewsletterSubscriptionSelectXpath = "//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input";
  protected static final String privacyPolicyCheckboxXpath = "//*[@id=\"content\"]/form/div/div/input[1]";
  protected static final String continueBtnXpath = "//*[@id=\"content\"]/form/div/div/input[2]";
  protected static final String successMessageXpath = "//*[@id=\"content\"]/p[1]";


//  Clicar em "My account"
//  Selecionar a opção "Register"
//  Preencher o formulário de registro com os dados exigidos.
//  Selecionar a opção “No” para Newsletter
//  Preencher a flag  “I have read and agree to the Privacy Policy “
//  Clicar em “Continue”
//  Verificar se o texto “Congratulations! Your new account has been successfully created!” está visível na tela


  public void goRegisterPage() {
    WebElement myAccountBtn = getWebElement(By.xpath(myAccountBtnXpath));
    myAccountBtn.click();
    WebElement registerBtn = getWebElement(By.xpath(RegisterBtnXpath));
    registerBtn.click();
  }

  public void inputRegisterData(String firstName, String lastName, String email, String phone, String password) {
    WebElement firstNameInput = getWebElement(By.xpath(firstNameInputXpath));
    firstNameInput.clear();
    firstNameInput.sendKeys(firstName);

    WebElement lastNameInput = getWebElement(By.xpath(lastNameInputXpath));
    lastNameInput.clear();
    lastNameInput.sendKeys(lastName);

    WebElement emailInput = getWebElement(By.xpath(emailInputXpath));
    emailInput.clear();
    emailInput.sendKeys(email);

    WebElement phoneInput = getWebElement(By.xpath(phoneInputXpath));
    phoneInput.clear();
    phoneInput.sendKeys(phone);

    WebElement passwordInput = getWebElement(By.xpath(passwordInputXpath));
    passwordInput.clear();
    passwordInput.sendKeys(password);

    WebElement confirmPasswordInput = getWebElement(By.xpath(confirmPasswordInputXpath));
    confirmPasswordInput.clear();
    confirmPasswordInput.sendKeys(password);

    WebElement noNewsletterSubscriptionSelect = getWebElement(By.xpath(noNewsletterSubscriptionSelectXpath));
    noNewsletterSubscriptionSelect.click();

    WebElement privacyPolicyCheckbox = getWebElement(By.xpath(privacyPolicyCheckboxXpath));
    privacyPolicyCheckbox.click();
  }

  public void pressContinueButton() {
    WebElement continueBtn = getWebElement(By.xpath(continueBtnXpath));
    continueBtn.click();
  }

  public String getSuccessMessage() {
    WebElement successMessage = getWebElement(By.xpath(successMessageXpath));
    return successMessage.getText();
  }
}
