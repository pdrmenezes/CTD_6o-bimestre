import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Api_Reqres {
  @Test
  public void api_test_01_list_users(){
    Response response = RestAssured.get("https://reqres.in/api/users?page=2");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1000L));
    validate.body(Matchers.containsString("rachel.howell@reqres.in"));
  }

  @Test
  public void api_test_02_single_user_by_id(){
    Response response = RestAssured.get("https://reqres.in/api/users/2");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1000L));
    validate.body(Matchers.containsString("janet.weaver@reqres.in"));
  }

  @Test
  public void api_test_03_single_user_not_found(){
    Response response = RestAssured.get("https://reqres.in/api/users/23");

    ValidatableResponse validate = response.then();
    validate.statusCode(404);
    validate.time(Matchers.lessThan(1500L));
  }

  @Test
  public void api_test_04_list_resource(){
    Response response = RestAssured.get("https://reqres.in/api/unknown");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1000L));
    validate.body(Matchers.containsString("cerulean"));
  }

  @Test
  public void api_test_05_single_resource_by_id(){
    Response response = RestAssured.get("https://reqres.in/api/unknown/2");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1000L));
    validate.body(Matchers.containsString("17-2031"));
  }

  @Test
  public void api_test_06_single_resource_not_found(){
    Response response = RestAssured.get("https://reqres.in/api/unknown/23");

    ValidatableResponse validate = response.then();
    validate.statusCode(404);
    validate.time(Matchers.lessThan(600L));
  }

  @Test
  public void api_test_07_create_user(){
    Response response = RestAssured.get("https://reqres.in/api/users");

    JSONObject request = new JSONObject();

    request.put("name", "morpheus");
    request.put("job", "farmer");
    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(request.toString())
        .when()
        .post("https://reqres.in/api/users")
        .then()
        .statusCode(201).log().all();

    ValidatableResponse validate = response.then();
    validate.statusCode(404);
    validate.time(Matchers.lessThan(600L));
  }

  @Test
  public void api_test_08_update_user_put(){
    JSONObject request = new JSONObject();

    request.put("name", "morphias");
    request.put("job", "leader");
    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(request.toString())
        .when()
        .put("https://reqres.in/api/users/2")
        .then()
        .statusCode(201).log().all();
  }

  @Test
  public void api_test_09_update_user_patch(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("job", "queen");

    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .patch("https://reqres.in/api/users/2")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_10_delete_user() {
    Response response = RestAssured.delete("https://reqres.in/api/users/2");

    ValidatableResponse validate = response.then();
    validate.statusCode(204);
    validate.time(Matchers.lessThan(1500L));
  }

  @Test
  public void api_test_11_register_successful(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", "eve.holt@reqres.in");
    jsonObject.put("password", "gunsnroses");

    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://reqres.in/api/register")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_12_register_unsuccessful(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", "eve.holt@reqres.in");
    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://reqres.in/api/register")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_13_login_successful(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", "eve.holt@reqres.in");
    jsonObject.put("password", "gunsnroses");
    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://reqres.in/api/login")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_13_login_unsuccessful(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("email", "pter@reqres.in");
    jsonObject.put("password", "pearljam");
    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://reqres.in/api/login")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_14_delayed_response(){
    Response response = RestAssured.get("https://reqres.in/api/users?delay=2");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(4000L));
    validate.time(Matchers.greaterThan(1500L));
    validate.body(Matchers.containsString("emma.wong@reqres.in"));
  }
}