//public class POST_Test01 {
//  @Test
//  public void test01(){
//    // instanciamos o objeto do tipo JSONObject(), que será o body da requisição
//    JSONObject request = new JSONObject();
//
//    request.put("name", "Ajeet");
//    request.put("job", "Teacher");
//    // configuramos no given() todos os parâmetros necessários para envio da requisição
//    given()
//    .header("Content-type", "application/json")
//    .contentType(ContentType.JSON)
//    .body(request.toJSONString())
//    // fazemos a requisição POST à API
//    .when()
//    .post("https://reqres.in/api/users")
//    // fazemos todas as validações necessárias
//    .then()
//    .statusCode(201).log().all();
//  }
//}