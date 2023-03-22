package FrontEnd.BugBankTest;

import FrontEnd.Pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
  private static LoginPage page;

  @BeforeEach
  public void setup() {
    page = new LoginPage();
    page.openApp();
  }

  @Test
  @Tag("Login")
  public void failedLoginFlowTest() throws InterruptedException {
    page.inputFullLoginData("a@a.c", "123456");
    page.pressAccessButton();
    Thread.sleep(2000);
    String failedLoginMessage = page.getFailedLoginMessage();

    assertTrue(failedLoginMessage.contains("Usuário ou senha inválido."));
    System.out.println("Warning Message: " + failedLoginMessage);
  }

  @Test
  @Tag("Login")
  public void incorrectEmailFormatFlowTest() {
    page.inputIncorretEmailFormat("a@a");
    page.pressAccessButton();

    String incorrectEmailFormatWarningMessage = page.getFieldWarningMessage();
    assertTrue(incorrectEmailFormatWarningMessage.contains("Formato inválido"));
    System.out.println("Warning Message: " + incorrectEmailFormatWarningMessage);
  }

  @Test
  @Tag("Login")
  public void requiredFieldsFlowTest() {
    page.inputPassword("123456");
    page.pressAccessButton();

    String requiredFieldWarningMessage = page.getFieldWarningMessage();
    System.out.println("Warning Message: " + requiredFieldWarningMessage);

    assertTrue(requiredFieldWarningMessage.contains("É campo obrigatório"));
  }

  @AfterEach
  public void teardown() {
    page.closeDriver();
    System.out.println("The driver has been closed.");
  }
}
