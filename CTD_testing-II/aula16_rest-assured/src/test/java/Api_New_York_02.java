import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Censo de esquilos no Central Park em 2018 kk
public class Api_New_York_02 {
  @Test
  public void api02_test01_GET(){
    Response response = RestAssured.get("https://data.cityofnewyork.us/resource/vfnx-vebw.json?hectare_squirrel_number=3");

    int statusCode = response.getStatusCode();
    assertEquals(statusCode, 200);

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.body(Matchers.containsString( "37F-PM-1014-03"));
  }
}
