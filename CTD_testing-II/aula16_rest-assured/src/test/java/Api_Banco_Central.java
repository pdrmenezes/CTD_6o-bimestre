import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


// Instituições brasileiras autorizadas a operar no SML com a Argentina
public class Api_Banco_Central {
  @Test
  public void api03_test01_GET(){
    Response response = RestAssured.get("https://olinda.bcb.gov.br/olinda/servico/SML/versao/v1/odata/InstituicoesBrasilArgentina?%24format=json&%24top=2");

    System.out.println(response.getBody().asString());

    ValidatableResponse validate = response.then();
    validate.body(Matchers.containsString("Bradesco"));
  }

}
