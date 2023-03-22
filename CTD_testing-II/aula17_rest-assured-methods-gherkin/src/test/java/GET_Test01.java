//public class GET_Test01 {
//  @Test
//  public void test01(){
//    Response response = RestAssured.get("https://reqres.in/api/users?page=2");
//
//  // possibilidade de consulta de diversas de diversas informações a partir da consulta ao endpoint
//    System.out.println(response.getBody().asString());
//    System.out.println(response.asString());
//    System.out.println(response.getStatusCode());
//    System.out.println(response.getHeader("content-type"));
//    System.out.println(response.getTime());
//  }
//
//  // validar status
//  int statusCode = response.getStatusCode();
//  Assert.assertEquals(statusCode, 200);
//
//  // validar conteúdo do body
//  String body = response.getBody().asString();
//  Assert.assertEquals(body.data.id[0].email, "michael.lawson@reqres.in");
//}