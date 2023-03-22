- JDK >18
- Maven >3.8.x
- Eclipse IDE / IntelliJ

1. Vamos começar criando o projeto. Vamos abrir o IDE do Eclipse e criar um novo projeto.

- Clique em File → New → Maven Project
- Estabelecemos o espaço de trabalho e clicamos em Seguinte.
- Selecionamos o arquétipo como maven-archetype-quickstart e clicamos em Seguinte.
- Fornecemos detalhes do ID do dispositivo, o ID do grupo, o nome e a descrição. Em seguida, clicamos em Finish.

2. Configurando o Junit:

- Adicionamos o REST Assured para maven ao arquivo pom.xml
  URL - https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit-platform.html
  ```
  <dependencies>
    [...]
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    [...]
  </dependencies>
  ```
- Criamos o arquivo XML do conjunto de testes para chamar a classe de teste, e colocamos, por exemplo, o seguinte código: testng.xml na pasta src\test\resources.
- Estabelecemos a configuração do plugin Surefire para executar o arquivo xml.

```
   <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>2.20</version>
        <configuration>
          <suiteXmlFiles>
            <suiteXmlFile>
              src\test\resources\testng.xml
            </suiteXmlFile>
          </suiteXmlFiles>
        </configuration>
   </plugin>
```

3. Configurando o REST Assured

- Adicionamos o Rest Assured para maven no arquivo pom.xml,
  URL — https://mvnrepository.com/artifact/io.rest-assured/rest-assured

```
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.0</version>
    <scope>test</scope>
</dependency>
```

4. Escrevendo o método de teste

- Crie uma classe chamada “SamplaTests.java”.
- Adicione à classe o seguinte método:

```
@Test
public void sampleLogin(){
  given().contentType("application/x-www-form-urlencoded; charset")
    .formParam("grant_type", "password")
    .formParam("username", "Test User")
    .formParam("password", "Test123&&")
    .when().post("http://demo6118645.mockable.io/login")
    .then().statusCode(200);
}
```

Execute o projeto de teste usando o comando: mvn clean surefire-report:report
