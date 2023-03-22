import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Api_Jsonplaceholder {

  @Test
  public void api_test_01_put(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", 11);
    jsonObject.put("name", "Pedro Menezes");
    jsonObject.put("username", "pdrmenezes");

    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://jsonplaceholder.typicode.com/users")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_02_get(){
    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1500L));
    validate.body(Matchers.containsString("Patricia"));
  }

  @Test
  public void api_test_03_put(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", 1);
    jsonObject.put("title", "Updating Post");
    jsonObject.put("body", "Hello world");
    jsonObject.put("userId", 1);

    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://jsonplaceholder.typicode.com/posts")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_04_post(){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("title", "New Post");
    jsonObject.put("body", "Hello world");
    jsonObject.put("userId", 1);

    given()
        .header("Content-type", "application/json")
        .contentType(ContentType.JSON)
        .body(jsonObject.toString())
        .when()
        .post("https://jsonplaceholder.typicode.com/posts")
        .then()
        .statusCode(201);
  }

  @Test
  public void api_test_05_get(){
    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/comments?postId=1");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1500L));
    validate.body(Matchers.containsString("Lew@alysha.tv"));
  }
  @Test
  public void api_test_06_get(){
    Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/4");

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThan(1500L));
    validate.body(Matchers.containsString("eum et est occaecati"));
  }
}
