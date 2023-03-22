import Pages.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageTest {
  private static SearchPage page;
  @BeforeEach
  public void setUp(){
    page = new SearchPage();
    page.openApp();
  }
  @Test
  @Tag("Regression")
  public void searchTest1() throws InterruptedException{
    page.searchByLocation("Punta Del Este");
    Thread.sleep(500);
    page.pressSearchButton();
    Thread.sleep(1000);
    String location = page.getLocation();

    assertTrue(location.contains("Villa Kantounes"));
    System.out.println("Location Name: " + location);
  }

  @Test
  @Tag("SmokeTest")
  public void searchTest2() throws InterruptedException{
    page.searchByLocation("Carilo");
    Thread.sleep(500);
    page.pressSearchButton();
    Thread.sleep(1000);
    String location = page.getLocation();

    assertTrue(location.contains("Carilo"));
    System.out.println("Location Name: " + location);
  }

  @Test
  public void searchTest3() throws InterruptedException{
    page.searchByLocation("Ranchos");
    Thread.sleep(500);
    page.pressSearchButton();
    Thread.sleep(1000);
    String location = page.getLocation();

    assertTrue(location.contains("La Soñada"));
    System.out.println("Location Name: " + location);
  }

  @AfterEach
  public void closeBrowser(){
    page.quitDriver();
    System.out.println("The driver has been closed.");
  }
}

// no terminal, na pasta do projeto, executar: < mvn clean surefire-report:report > pra rodar todos os testes
// e depois, pra embelezar: < mvn site -DgenerateReports=false >
// pra executar testes com uma Tag específica < mvn clean surefire-report:report -Dgroups="Regression" >