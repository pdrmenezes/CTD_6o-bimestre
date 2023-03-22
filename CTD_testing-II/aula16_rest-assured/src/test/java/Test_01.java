import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test_01 {
  @BeforeEach
  public void printLine(){
    System.out.println("---------------------");
  }

  @Test
  public void api01_test01_GET(){
    Response response = RestAssured.get("https://reqres.in/api/users");

    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThanOrEqualTo(3000L));
    validate.contentType(ContentType.JSON);
  }

  @Test
  public void api02_test01_GET(){
    Response response = RestAssured.get("https://api.openweathermap.org/data/2.5/weather?q=Curitiba&appid=f548280284dd2a302a401bb70d0f272a&units=metric");

    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThanOrEqualTo(5000L));
    validate.contentType(ContentType.JSON);
  }

  @Test
  public void api03_test01_GET(){
    Response response = RestAssured.get("https://meowfacts.herokuapp.com/?id=3");

    System.out.println(response.getStatusCode());
    System.out.println(response.getTime());
    System.out.println(response.getBody().asString());

    ValidatableResponse validate = response.then();
    validate.statusCode(200);
    validate.time(Matchers.lessThanOrEqualTo(5000L));
    validate.contentType(ContentType.JSON);
  }
}
