// certifica-se que a rota básica está funcionando sem adicionar muita complexidade ao conjunto de testes.
// se ele falhar não será preciso explorar nenhum outro teste para essa URL até o problema ser resolvido

@Test
public void givenUserDoesNotExist_whenUserInfoIsRetrieved_then404IsReceived() throws ClientProtocolException, IOException {
  // Given
  String name = RandomStringUtils.randomAlphabetic(8);
  HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);

  // When
  HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

  // Then
  assertThat(httpResponse.getStatusLine().getStatusCode(), equalTp(HttpStatus.SC_NOT_FOUND));
}