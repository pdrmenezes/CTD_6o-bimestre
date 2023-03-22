## AAA model

Criação de testes usando o modelo AAA: Arrange, Act, Assert, ou: Prepare, Aja, Valide

### Arrange

- configuramos tudo o que for preciso para que o nosso teste seja executado: inicializamos variáveis, criamos alguns simuladores de teste como mocks ou spies, entre outros.

### Act

- executamos o nosso teste, chamando a função ou método que queremos testar.

### Assert

- verifica-se se a operação realizada no passo anterior produziu o resultado desejado. Assim, saberemos se o teste foi bem-sucedido ou falhou.

```js
descibe("Quando abrimos o Hook", () => {
  test("O Hook deveria estar aberto", () => {
    // Arrange
    const { result } = hookSetup();

    // Act
    act(() => {
      result.current.open();
    });

    // Assert
    expect(result.current.isOpen).toBeTruthy();
  });
});
```

## Tipos de Teste

### E2E - End-to-End

Normalmente, bots testam a aplicação da mesma forma que um usuário real. Necessitam muitos recursos e configurações. Além disso, são difíceis de desenvolver. Normalmente, existem equipes completas dedicadas a este desenvolvimento por conta do tempo necessário para sua construção.

### Testes de Integração

São os mais difíceis de se manter e se desenvolver. Além disso, são mais lentos do que os testes de unidade, uma vez que testam funcionalidades em sua totalidade, normalmente com persistência de dados. No entanto, fornecem muito mais certeza do que os testes de unidade.

Na prática, esses testes de integração somente são realizados nas funcionalidades mais importantes, pois são muito caros. A indústria tende sempre a utilizar testes de unidade.

### Testes de Unidade

Verificam o funcionamento da menor unidade de um código da aplicação, independentemente da sua interação com outras partes do nosso código. Precisam de intervenção manual: devemos codificá-los e mantê-los. Portanto, devem ser feitos sempre que pudermos garantir que nosso aplicativo funcione conforme solicitado.

Nos testes de unidade devemos utilizar mocks, que são imitações ou falsas unidades que simulam o comportamento das unidades reais.

Algumas das vantagens de entender o que são testes de unidade e sua importância vão além da tranquilidade para os desenvolvedores:

- Garantem que a função será mantida após qualquer alteração na base de código.
- Impedem problemas futuros.
- Garantem a qualidade do software.
- Incrementam a produtividade do desenvolvedor.
- Melhoram a velocidade do código.
- Garantem a eficácia do ambiente de aplicação escolhido.

### Testes Estáticos

São os mais simples e menos caros de se realizar, sendo executados automaticamente pelo nosso IDE (como Visual Studio Code) ou por meio do uso de outras ferramentas que já vimos, como os linters (ESLint), que nos alertam sobre os erros comuns no nosso código.

## Using Jest

```
yarn add --dev jest
```

```
npm i --save-dev jest
```

Assim que ele estiver instalado, configurar o Jest no package.json adicionando o script:

```JSON
"scripts: {
  "test": "jest"
}
```

## Casos de Uso

- Testar um componente separadamente.
- Testar a API pública de um componente (propriedades, métodos e eventos).
- Testar a interação básica do usuário (por exemplo, os cliques).
- Certificar-se da saída do DOM de um componente.
- Verificar se os eventos são acionados quando se é esperado.

## Asserts

O Jest adiciona métodos e objetos ao ambiente global dos nossos arquivos de teste. Para utilizá-los, não é preciso fazer nenhuma importação. No entanto, se preferirmos importá-los, basta escrever:

```js
import { describe, expect, test } from "@jest/globals";
```

### Describe

- Cria um bloco que agrupa vários testes relacionados. Por exemplo, se tivermos um objeto minhaBebida, que deve ser deliciosa, mas não azeda

```js
const minhaBebida = {
  deliciosa: true,
  azeda: false,
};

describe("minha bebida", () => {
  test("é deliciosa", () => {
    expect(miBebida.deliciosa).toBeTruthy();
  });

  test("é azeda", () => {
    expect(minhaBebida.azeda).toBeFalsy();
  });
});
```

### Expect

- A função expect é usada toda vez que pretendemos comprovar um valor. Raramente esta função será usada sozinha. Em vez disso, será utilizada junto a uma função matcher para verificar algo sobre um valor. É mais fácil compreender isso com um exemplo. Digamos que temos uma função bestFlavour(), que esperamos que retorne “uva”

```js
describe("bestFlavour()", () => {
  test("o melhor sabor deveria ser uva", () => {
    // Act
    const result = bestFlavour();
    // Assert
    expect(result).toBe("uva");
  });
});
```

### Test

- Tudo o que é necessário para realizar um teste é executar a função de test. Por exemplo, digamos que há uma função isRaining(), que deve ser zero

```js
describe("No início", () => {
  test("não deveria estar chovendo", () => {
    // Act
    const result = isRaining();
    // Assert
    expect(isRaining()).toBe(0);
  });
});
```

### Describe + Expect + Test

- Vamos supor que temos uma funçãoAsync que recebe dois callbacks (callback1 e callback2), que chamará ambos de forma assíncrona em uma ordem desconhecida

```js
describe("doAsync", () => {
  test("Invoca ambos os callbacks", () => {
    // expect.assertions() verifica se um determinado n´¨mero de comprovações é chamado durante um teste. Isso costuma ser útil quando o código assíncrono é comprovado, para certificar-se de que as duas verificações de uma devolução de callback foram realmente chamadas
    expect.assertions(2);
    function callback1(data) {
      expect(data).toBeTruthy();
    }
    function callback2(data) {
      expect(data).toBeTruthy();
    }

    doAsync(callback1, callback2);
  });
});
```

## Mocks

Quando escrevemos testes, precisamos muitas vezes simular partes do nosso sistema para que esses testes sejam possíveis e os resultados sejam reproduzíveis. O desenvolvimento de testes de unidade por meio de mocks faz com que o desenvolvedor pense de forma diferente na hora de implementar um sistema.

Vamos imaginar que estamos testando a implementação de uma função forEach, que invoca um callback para cada elemento de um array proporcionado.

```js
function forEach(items, callback) {
  for (let index = 0; index < items.length; index++) {
    callback(items[index]);
  }
}
```

Para testar essa função, podemos utilizar uma função de simulação e revisar o estado da simulação para nos certificar de que o callback é invocado como esperado.

```js
const mockCallback = jest.fn((x) => 42 + x);
forEach([0, 1], mockCallback);

// A função mock é chamada duas vezes
expect(mockCallback.mock.calls.length).toBe(2);

// O primeiro argumento da chamada à função deve ser 0
expect(mockCallback).toBeCalledWith(0);
```

### Exemplo

```js
export const createUser = async () => {
  const response = await fetch("https://api.github.com/users", {
    method: "POST",
  });
  const userId = await response.text();
  return userId;
};
```

Escreveremos um teste para a função createUser(). O teste “simulará” a função fetch para nos certificarmos de que ela será chamada sem que uma requisição ao endpoint seja realmente realizada. No entanto, teremos também que imitar o valor de retorno da função fetch por meio de uma resposta (encapsulada em uma promessa).

```js
jest.mock("node-fetch");
import fetch from "node-fetch";
const { Response } = jest.requireActual("node-fetch");
```

## Coverage

Coverage, ou cobertura dos testes, indica quais partes do código são executadas. Por exemplo: se temos um código de dez linhas e uma cobertura de 50%, isso significa que somente a metade das linhas deste código (ou seja, cinco linhas) está sendo executada ao longo dos testes.

### Adicionando coverage

Assim que o Jest estiver instalado, a configuração do coverage do projeto é muito simples. Basta adicionar o seguinte script ao arquivo package.json gerado:

```json
...
"scripts": {
   "test": "jest  --coverage",
  "test-coverage": "jest --coverage"
}
…

…
"jest": {
     "collectCoverage": true,
 }
…
```

### Gerando relatórios

```
npm test -- --coverage
```

```
npm test -- --coverage
```

## TDD - Test-driven development

A ideia central do TDD é que, em primeiro lugar, é preciso escrever os testes da aplicação e então implementar o código que vai fazer com que funcionem. Isto pode parecer um pouco estranho, mas trata-se de uma ideia audaciosa que traz vários benefícios. Vamos ver quais são:

- Diminuição da quantidade de falhas.
- Foco nas características importantes para o projeto.
- Permite realizar testes de regressão para observar se o sistema continua funcionando mesmo após várias alterações.
- TDD é a prática repetida de ciclos curtos, divididos em três fases:
  - vermelha: teste reprovado
  - verde: teste bem-sucedido
  - azul: refatoração
