package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
//    Botão iniciar sessão:
//    By Id -
//    By XPath - //*[@id="root"]/header/nav/a[2]
//    By Text - Iniciar sesión
//    By Class - "btn btn-secundario btn-xs"
//    By CssSelector - #root > header > nav > a:nth-child(2)

//    Input email:
//    By Id - email
//    By XPath - //*[@id="email"]
//    By Text - Correo Electrónico
//    By Class - "text-input input"
//    By CssSelector - #email

//    Input senha:
//    By Id - password
//    By XPath - //*[@id="password"]
//    By Text - Contraseña
//    By Clas - "text-input input"
//    By CssSelector - #password

//    Botão ingresar:
//    By Id -
//    By XPath - //*[@id="root"]/main/div/form/button
//    By Text - Ingresar
//    By Clas - "btn btn-primario btn-sm"
//    By CssSelector - #root > main > div > form > button

//    Mensagem de erro:
//    By Id -
//    By XPath - //*[@id="root"]/main/div/form/p[1]
//    By Text - Sus credenciales son inválidas. Por favor, vuelva a intentarlo
//    By Class - "form-feedback"
//    By CssSelector - #root > main > div > form > p.form-feedback

    // xpath
    protected static final String homeLoginButtonXpath = "//*[@id=\"root\"]/header/nav/a[2]";
    protected static final String emailInputXpath = "//*[@id=\"email\"]";
    protected static final String passwordInputXpath = "//*[@id=\"password\"]";
    protected static final String loginButtonXpath = "//*[@id=\"root\"]/main/div/form/button";
    protected static final String loggedUserNameXpath = "//*[@id=\"root\"]/header/nav/div/div/div/p[2]";
    protected static final String failedLoginMessageXpath = "//*[@id=\"root\"]/main/div/form/p[1]";

    // id
    protected static final String emailInputId = "email";

    // class
    protected static final String loginButtonClass = "btn-primario";
    // css selector
    protected static final String homeLoginButtonCssSelector = "#root > header > nav > a:nth-child(2)";

    public void goToLoginPage() {
//        WebElement homeLoginButton = getWebElement(By.xpath(homeLoginButtonXpath));
        WebElement homeLoginButton = getWebElement(By.cssSelector(homeLoginButtonCssSelector));
        homeLoginButton.click();
    }

    public void inputLoginData(String email, String password) {
//        WebElement emailInput = getWebElement(By.xpath(emailInputXpath));
        WebElement emailInput = getWebElement(By.id(emailInputId));
        emailInput.clear(); // clears the field to make sure there are no surprises
        emailInput.sendKeys(email);

        WebElement passwordInput = getWebElement(By.xpath(passwordInputXpath));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void pressLoginButton(){
//        WebElement loginButton = getWebElement(By.xpath(loginButtonXpath));
        WebElement loginButton = getWebElement(By.className(loginButtonClass));
        loginButton.click();
    }
    public String getUser(){
        WebElement user = getWebElement(By.xpath(loggedUserNameXpath));
        return user.getText();
    }

    public String getFailedLoginMessage(){
        WebElement failedLoginMessage = getWebElement(By.xpath(failedLoginMessageXpath));
        return failedLoginMessage.getText();
    }
}