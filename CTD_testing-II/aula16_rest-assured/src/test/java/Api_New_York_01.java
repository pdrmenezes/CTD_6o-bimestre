import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Ano(s) em que 213 galões de água foram consumidos por dia, por pessoa
public class Api_New_York_01 {
  @Test
  public void api01_test01_GET(){
    Response response = RestAssured.get("https://data.cityofnewyork.us/resource/ia2d-e54m.json?$where=per_capita_gallons_per_person_per_day=213");

    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());

    int statusCode = response.getStatusCode();
    assertEquals(statusCode, 200);

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.contentType(ContentType.JSON);
    validate.body(Matchers.containsString("1979"));
  }
}
