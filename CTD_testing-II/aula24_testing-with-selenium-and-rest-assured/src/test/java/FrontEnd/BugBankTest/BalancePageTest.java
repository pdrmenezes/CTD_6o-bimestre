package FrontEnd.BugBankTest;

import FrontEnd.Pages.BalancePage;
import FrontEnd.Pages.LoggedPage;
import FrontEnd.Pages.LoginPage;
import FrontEnd.Pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancePageTest {
  private static BalancePage page;
  private static RegisterPage registerPage;
  private static LoginPage loginPage;
  private static LoggedPage loggedPage;

  @BeforeEach
  public void setup() throws InterruptedException {
    page = new BalancePage();
    page.openApp();
    registerPage = new RegisterPage();
    registerPage.goToRegisterForm();
    registerPage.inputRegisterData("a@a.a", "a", "123", "123");
    registerPage.toggleCreateAccountWithBalance();
    registerPage.pressRegisterButton();
    Thread.sleep(500);
    registerPage.closeToaster();
    loginPage = new LoginPage();
    loginPage.inputFullLoginData("a@a.a", "123");
    loginPage.pressAccessButton();
    Thread.sleep(500);
    loggedPage = new LoggedPage();
    loggedPage.goToBalancePage();
    Thread.sleep(500);
  }

  @Test
  @Tag("Balance")
  public void checkBalanceAndTransaction() throws InterruptedException {
    String balance = page.getTotalBalanceText();
    String transactionAmount = page.getTransactionAmount();
    String transactionDescription = page.getTransactionDescription();
    System.out.println("balance: " + balance);
    System.out.println("transaction amount: " + transactionAmount);
    System.out.println("transaction description: " + transactionDescription);

    assertTrue(balance.contains("1.000") && transactionAmount.contains("1.000") && transactionDescription.contains("Saldo adicionado ao abrir conta"));
  }

  @AfterEach
  public void teardown() {
    page.closeDriver();
    System.out.println("The driver has been closed.");
  }
}
