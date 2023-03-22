import Pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {
    private static LoginPage page;

    @BeforeEach
    public void setUp(){
        page = new LoginPage();
        page.openApp();
    }

    @Test
    public void loginFlowTest() throws InterruptedException {
        page.goToLoginPage();
        page.inputLoginData("tgomes@digitalhouse.com", "turma01");
        Thread.sleep(1500);
        page.pressLoginButton();
        Thread.sleep(2000);
        String user = page.getUser();

        assertTrue(user.contains("Tiago Gomes"));
        System.out.println("Logged user name: " + user);
    }

    @Test
    public void failedLoginFlowTest() throws InterruptedException {
        page.goToLoginPage();
        page.inputLoginData("tgomes@digitalhouse.com", "turma00");
        Thread.sleep(1500);
        page.pressLoginButton();
        Thread.sleep(2000);
        String errorMessage = page.getFailedLoginMessage();

        assertTrue(errorMessage.contains("Sus credenciales son inválidas. Por favor, vuelva a intentarlo"));
        System.out.println("Error Message: " + errorMessage);
    }

    @AfterEach
    public void closeBrowser(){
        page.quitDriver();
        System.out.println("The driver has been closed.");
    }
}
