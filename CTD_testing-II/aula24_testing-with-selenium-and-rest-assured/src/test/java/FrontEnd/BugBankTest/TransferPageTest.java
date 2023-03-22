package FrontEnd.BugBankTest;

import FrontEnd.Pages.LoggedPage;
import FrontEnd.Pages.LoginPage;
import FrontEnd.Pages.RegisterPage;
import FrontEnd.Pages.TransferPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferPageTest {
  private static TransferPage page;
  private static RegisterPage registerPage;
  private static LoginPage loginPage;
  private static LoggedPage loggedPage;

  @BeforeEach
  public void setup() throws InterruptedException {
    page = new TransferPage();
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
    loggedPage.goToTransferPage();
    Thread.sleep(500);
  }

  @Test
  @Tag("Transfer")
  public void transferFlowTest() throws InterruptedException {
    page.inputTransferAmount("500");
    page.pressTransferButton();
    Thread.sleep(500);

    String successMessage = page.getWarningToasterMessage();
    System.out.println(successMessage);

    assertTrue(successMessage.contains("Transferencia realizada com sucesso"));
  }

  @AfterEach
  public void teardown() {
    page.closeDriver();
    System.out.println("The driver has been closed.");
  }
}
