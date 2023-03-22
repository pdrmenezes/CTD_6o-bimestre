package BackEnd;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class BreachesApi_RestAssured {
//  https://haveibeenpwned.com/api/v2/

  @Test
  public void api_test_01_list_all_breaches() {
    Response response = RestAssured.get("https://haveibeenpwned.com/api/v2/breaches");
    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(4000L));
    validate.body(Matchers.containsString("bittorrent.com"));
  }

  @Test
  public void api_test_02_list_breach_by_site_name() {
    Response response = RestAssured.get("https://haveibeenpwned.com/api/v2/breach/bittorrent");
    System.out.println(response.getBody().asString());
    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(10000L));
    validate.body(Matchers.containsString("Passwords"));
  }

  @Test
  public void api_test_03_list_breach_by_site_name() {
    Response response = RestAssured.get("https://haveibeenpwned.com/api/v2/breach/yahoo");
    System.out.println(response.getBody().asString());
    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(4000L));
    validate.body(Matchers.containsString("Passwords"));
  }

  @Test
  public void api_test_04_list_breach_by_site_name_not_breached() {
    Response response = RestAssured.get("https://haveibeenpwned.com/api/v2/breach/youtube");
    System.out.println(response.getBody().asString());
    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());

    ValidatableResponse validate = response.then();
    validate.statusCode(404);
    validate.time(Matchers.lessThan(4000L));
  }
}
