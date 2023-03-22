# Rest Assured + Gherkin

Rest Assured permite uso de Gherkin, que define uma linguagem compreensível por humanos e computadores. Com ele podemos descrever as funcionalidades definindo o comportamento do software sem entrar na sua implementação.

Para começar a usar BDD precisamos de 5 palavras:

- FEATURE: nome da funcionalidade que vamos testar.
- SCENARIO: descreve cada cenário a ser testado.
- GIVEN: contexto para o cenário em que o teste será executado.
- WHEN: conjunto de ações que lançam o teste.
- THEN: especifica o resultado do teste esperado.

Usaremos as seguintes dependências:
```java
<dependencies>
    <dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>rest-assured</artifactId>
      <version>5.1.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.1</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20160212</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```
