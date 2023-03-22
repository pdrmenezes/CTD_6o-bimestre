package FrontEnd.BugBankTest;

import FrontEnd.Pages.RegisterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPageTest {
  private static RegisterPage page;

  @BeforeEach
  public void setup() {
    page = new RegisterPage();
    page.openApp();
    page.goToRegisterForm();
  }

  @Test
  @Tag("Register")
  public void registerWithoutNameFlowTest() throws InterruptedException {
    page.inputRegisterData("a@a.c", "", "123456", "123456");
    page.pressRegisterButton();
    Thread.sleep(2000);
    String warningToasterMessage = page.getWarningToasterMessage();

    assertTrue(warningToasterMessage.contains("Nome não pode ser vazio."));
    System.out.println("Warning Message: " + warningToasterMessage);
  }

  @Test
  @Tag("Register")
  public void incorrectEmailFormatFlowTest() {
    page.inputRegisterData("a@a", "a", "123456", "123456");
    page.pressRegisterButton();

    String incorrectEmailFormatWarningMessage = page.getFieldWarningMessage();
    assertTrue(incorrectEmailFormatWarningMessage.contains("Formato inválido"));
    System.out.println("Warning Message: " + incorrectEmailFormatWarningMessage);
  }

  @Test
  @Tag("Register")
  public void requiredFieldsFlowTest() {
    page.inputRegisterData("", "a", "123456", "123456");
    page.pressRegisterButton();

    String requiredFieldWarningMessage = page.getFieldWarningMessage();
    assertTrue(requiredFieldWarningMessage.contains("É campo obrigatório"));
    System.out.println("Warning Message: " + requiredFieldWarningMessage);
  }

  @Test
  @Tag("Register")
  public void successfullyRegisterWithStartingBalanceFlowTest() throws InterruptedException {
    page.inputRegisterData("a@a.a", "a", "123", "123");
    page.toggleCreateAccountWithBalance();
    page.pressRegisterButton();


    Thread.sleep(500);
    String successfullyCreatedAccountMessage = page.getWarningToasterMessage();
    assertTrue(successfullyCreatedAccountMessage.contains("foi criada com sucesso"));
    System.out.println("Success Message: " + successfullyCreatedAccountMessage);
  }

  @Test
  @Tag("Register")
  public void successfullyRegisterWithoutStartingBalanceFlowTest() throws InterruptedException {
    page.inputRegisterData("b@b.b", "b", "123", "123");
    page.pressRegisterButton();

    Thread.sleep(500);
    String successfullyCreatedAccountMessage = page.getWarningToasterMessage();
    System.out.println("Success Message: " + successfullyCreatedAccountMessage);

    assertTrue(successfullyCreatedAccountMessage.contains("foi criada com sucesso"));
  }

  @AfterEach
  public void teardown() {
    page.closeDriver();
    System.out.println("The driver has been closed.");
  }
}
