# React Testing Library (RTL)

## Sintaxe Básica

- render(): este método é o responsável pela renderização do componente que queremos testar. Devemos passar para ele um componente React, e ele nos devolve o componente renderizado dentro de um container que é inserido no document.body

```js
import { render } from "@testing-library/react";

render(Submit);
```

- screen: este objeto nos permite acessar os diferentes métodos de pesquisa (queries) para acessar os diferentes elementos do DOM com os quais queremos interagir.

As queries são formadas por um prefixo (que determina o valor de retorno e os casos em que a pesquisa deve lançar um erro) e um sufixo(que determina o atributo através do qual a pesquisa será executada)

### Prefixos

#### Para acessar elementos de forma individual

- getBy: devolve o nó correspondente para uma consulta e gera um erro descritivo caso não haja elementos correspondentes, ou se mais de uma correspondência for encontrada (se esperamos mais de um elemento, o getAllBy deve ser utilizado no seu lugar)
- queryBy: devolve o nó correspondente para uma consulta e devolve nulo caso não haja correspondências. É útil para verificar que um elemento não está presente. Se mais de uma correspondência for encontrada, ele lança um erro (se isso acontecer, o queryAllBy deve ser utilizado no seu lugar)

#### Para acessar múltiplos elementos

- getAllBy: devolve um array de todos os nós correspondentes para uma consulta e, caso nenhum elemento coincida, lança um erro
- queryAllBy: devolve um array de todos os nós correspondentes para uma consulta e devolve um array vazio ([]) se nenhum elemento coincide
- findAllBy: devolve uma promessa (assíncrono) que é resolvida em um array de elementos quando se encontram elementos correspondentes à consulta fornecida. A promessa será rejeitada se, após um tempo de espera padronizado de 1000 ms, nenhum elemento for encontrado

### Sufixos

#### Consultas acessíveis para todos

- Role: ele pode ser utilizado para consultar cada elemento exibido na árvore de acessibilidade. Por exemplo, se estamos buscando um botão, podemos utilizar o role “button”. [Veja todos os papéis disponíveis](https://developer.mozilla.org/en-US/docs/Web/Accessibility/ARIA/ARIA_Techniques#roles)
- LabelText: esse método é ótimo para os campos de formulário. Navegando através de um formulário de website, os usuários encontram elementos utilizando elementos do tipo label. Este método simula esse comportamento, sendo necessário utilizá-lo nesses casos
- PlaceholderText: no caso daqueles inputs que não possuem tag do tipo label associada, podemos utilizar o texto do placeholder para acessá-los.
- Text: fora dos formulários, o conteúdo de texto é a forma mais utilizada pelos usuários para encontrar elementos. Esse método pode ser utilizado para encontrar elementos não interativos (como divs ou parágrafos).
- DisplayValue: recebe o valor atual de um elemento do formulário. Pode ser útil ao se navegar em uma página com valores completados

#### Consultas semânticas

- AltText: se o elemento admite texto alternativo (por exemplo, no caso da tag img), essa query pode ser utilizada para encontrar esse elemento
- Title: embora esse seletor possa ser usado, lembre-se de que o atributo title não é lido de forma consistente pelos leitores de tela e não é visível por padrão para usuários com visão

#### Id de teste

- TestId: devemos considerar que o usuário não pode visualizá-los (nem escutá-los), sendo recomendáveis, portanto, somente nos casos em que não é possível fazer com que correspondam por papel ou texto, ou em que essa correspondência não faça sentido (por exemplo, se o texto for dinâmico)

### Prefixos + Sufixos

Combinando alguns prefixos com sufixos, podemos acessar um elemento dentro do container que gera o método render:

```js
import { render, screen } from "@testing-library/react";

render(Submit);

const button = screen.getByRole("button", { name: "Enviar" });
```

### Outros métodos

#### fireEvent()

Permite disparar um evento do DOM sobre um elemento específico, por exemplo, um botão. [Lista de eventos que podem ser lançados pelo fireEvent()](https://github.com/testing-library/dom-testing-library/blob/11fc7731e5081fd0994bf2da84d688fdb592eda7/src/event-map.js)

```js
import { render, screen, fireEvent } from "@testing-library/react";

render(<button>Submit</button>);

const button = screen.getByRole("button", { name: "Enviar" });
fireEvent.click(button);
```

#### userEvent()

Este método é fornecido pela biblioteca user-event e serve para simular interações com nossa aplicação por parte de pessoas usuárias, disparando eventos de forma semelhante ao que aconteceria se essa interação ocorresse no navegador. Embora na prática seja permitido disparar eventos como se estivéssemos utilizando fireEvent(), a diferença principal ao se utilizar este método é - como mencionado anteriormente - que os eventos são disparados como se uma pessoa tivesse interagido com a aplicação através do navegador. Isto permite detectar casos em que o evento não pode ser disparado pelo usuário, como por exemplo, no caso de existir algum bug.

Para fazer uso dessa biblioteca, podemos utilizar o método setup() dentro de cada teste. Após isso, poderemos disparar o evento que queremos, dependendo do caso do nosso teste.

```js
import { render, screen, fireEvent } from "@testing-library/react";
import userEvent from '@testing-library/user-event'

test('clique no botão', async () => {
 const userEvent = userEvent.setup()
 render(<button>Submit</button>);
 await userEvent.click(screen.getByRole('button', {name: “Enviar”}))
 // Assertions abaixo...
})
```

#### Matchers

RTL utiliza a jest-dom, uma biblioteca que fornece um conjunto de métodos adicionais para analisar determinado comportamento no DOM. Alguns dos “matchers” mais utilizados:

- toBeInTheDocument(): verifica se determinado elemento está presente no documento.
- toBeDisabled(): verifica se determinados elementos (por exemplo, um botão) estão desabilitados da perspectiva do usuário.
- toHaveTextContent(): permite verificar se um elemento possui um texto determinado.

[Lista de todos os matchers oferecidos pelo jest-dom.](https://github.com/testing-library/jest-dom#custom-matchers)

## Testando Componente

O componente a seguir recebe o parâmetro “estado” e, com base no seu valor, nos indica - por meio de uma mensagem - qual é a situação do carro de corrida. As condições que podem ser testadas são:

- Se eu receber “partida” como valor de “estado”, deveria receber uma mensagem afirmando que o carro acelera a toda velocidade.
- Se eu receber “espera” como valor de “estado”, deveria receber uma mensagem afirmando que o carro está pronto para partir.
- Se eu receber qualquer outro valor, deveria receber uma mensagem de erro.

```js
const CarroDeCorrida = ({ estado }) => {
  const obterMensagensDoEstado = () => {
    if (estado === "largada") {
      return "O carro parte a toda velocidade";
    } else if (estado === "espera") {
      return "O carro está pronto para partir";
    } else {
      return "Por favor, revise o estado da corrida";
    }
  };

  return <p>{obterMensagensDoEstado()}</p>;
};

export default CarroDeCorrida;
```

Para começar a escrever o nosso teste, devemos criar o nosso arquivo de teste, para o qual importaremos o nosso componente. Diferentemente dos casos anteriores, deveremos agora importar a partir de @testing-library/react as funções screen e render. Em seguida, criaremos o nosso describe e um teste vazio. Esse será o ponto de partida para testar o nosso componente

```js
import CarroDeCorrida from "./CarroDeCorrida";
import { render, screen } from "@testing-library/react";

describe("O componente CarroDeCorrida", () => {
  test("é renderizado corretamente", () => {});
});
```

Como primeiro teste, vamos validar se nosso componente aparece na tela e se o código é executado corretamente. Este seria um teste básico muito simples para a maioria dos componentes. Para isso devemos, em primeiro lugar, renderizá-lo. Utilizaremos a função render, que recebe um componente como parâmetro. Essa função carrega o nosso componente dentro do ambiente virtual gerado para que o Jest consiga avaliar se as nossas condições são cumpridas. Tudo isso acontece por trás dos panos, sem ser exibido na tela.

```js
import CarroDeCorrida from "./CarroDeCorrida";
import { render, screen } from "@testing-library/react";

describe("O componente CarroDeCorrida", () => {
  test("é renderizado corretamente", () => {
    render(<CarroDeCorrida />);
  });
});
```

Assim que o nosso componente estiver renderizado, devemos verificar se algo é exibido na nossa tela. Não nos esqueçamos de que cada teste deve ter pelo menos uma asserção!

Para isso, utilizaremos a segunda função que importamos: screen. Ela nos permite receber ou encontrar elementos da nossa “tela” (embora não visualizemos o elemento renderizado, o ambiente gerado simula o conteúdo da forma como seria visualizado no compilado final com o código HTML, CSS e JavaScript gerado pelo React).

Nas próximas duas linhas, indicaremos ao nosso teste que queremos buscar um elemento na nossa tela que contenha um texto que coincida com este: “Por favor, revise o estado da corrida” (pela forma como escrevemos o nosso componente, como não recebemos o parâmetro “estado”, é isso o que deveria ser renderizado e visualizado na nossa tela).

A função getByText nos permite utilizar expressões regulares ou strings para realizar as pesquisas de texto. Neste caso, utilizaremos um string.
Na última linha do nosso teste, basta realizar a asserção e indicar que estamos esperando que o elemento selecionado esteja no nosso documento

```js
import CarroDeCorrida from "./CarroDeCorrida";
import { render, screen } from "@testing-library/react";

describe("O componente CarroDeCorrida", () => {
  test("é renderizado corretamente", () => {
    render(<CarroDeCorrida />);

    const componente = screen.getByText("Por favor, revise o estado da corrida");

    expect(componente).toBeInTheDocument();
  });
});
```

Para finalizar e completar os nossos testes, necessitamos poder usar diferentes propriedades do nosso componente para simular os outros possíveis casos de uso que levantamos. Para isso, dentro do nosso teste, ao renderizar o nosso componente, basta passar para ele os parâmetros desejados. Afinal, estamos escrevendo JavaScript e, mais especificamente, utilizando a sintaxe do React, portanto, a lógica de escrita de componentes e parâmetros será exatamente igual.

```js
import CarroDeCorrida from "./CarroDeCorrida";
import { render, screen } from "@testing-library/react";

describe("O componente CarroDeCorrida", () => {
  test("é renderizado corretamente e exibe a mensagem de erro se não houver estado da corrida", () => {
    render(<CarroDeCorrida />);
    const componente = screen.getByText("Por favor, revise o estado da corrida");

    expect(componente).toBeInTheDocument();
  });

  test("exibe a mensagem de partida", () => {
    render(<CarroDeCorrida estado={"partida"} />);

    const componente = screen.getByText("O carro parte a toda velocidade");

    expect(componente).toBeInTheDocument();
  });

  test("exibe a mensagem de espera", () => {
    render(<CarroDeCorrida estado={"espera"} />);

    const componente = screen.getByText("O carro está pronto para partir");

    expect(componente).toBeInTheDocument();
  });
});
```

## Testando Hook

Em mais de uma ocasião, nos depararemos com componentes complexos, que não somente recebem props, como também implementam algum tipo de lógica utilizando Hooks. Em alguns casos, esses Hooks realizam tarefas simples que impactam no componente de forma relativamente imediata. Isto significa que provavelmente poderemos testar o comportamento dos Hooks avaliando o do componente, como visto no ponto anterior.

No entanto, existem outros cenários em que os Hooks adquirem uma certa complexidade (por exemplo, porque eles realizam algum request a uma API, ou porque precisamos “re-renderizar” o componente com diferentes props, entre outros). Neste caso, testar o comportamento dos Hooks da forma habitual pode ser um pouco complexo.

Felizmente existe uma alternativa. Podemos implementar o método renderHook, proporcionado por RTL. Esse método nos permite “simular” o comportamento de qualquer Hook, podendo assim testar os casos de uso e os resultados esperados.

```js
import { useState, useCallback } from "react";

export default function useContador() {
  const [contador, setContador] = useState(0);
  const incrementar = useCallback(() => setContador((x) => x + 1), []);

  return { contador, incrementar };
}
```

Para testar o seu funcionamento, utilizamos o método renderHook, fornecido pela RTL. Primeiro, testamos o estado inicial do contador:

```js
import { renderHook } from "@testing-library/react";
import useContador from "./useContador";

test("devolve o estado inicial e o método incrementar", () => {
  const { result } = renderHook(() => useContador());

  expect(result.current.contador).toBe(0);
  expect(typeof result.current.incrementar).toBe("function");
});
```

Como podemos ver, o método renderHook nos devolve um objeto que contém a propriedade current. Essa propriedade, por sua vez, é um objeto que contém o último valor de retorno do Hook (no nosso caso, a variável “contador” e a função “incrementar”). Assim, podemos testar os seus valores iniciais através da nossa asserção.

Da mesma forma, podemos verificar o que acontece quando atualizamos o valor do contador através do método “incrementar”:

```js
import { renderHook } from "@testing-library/react";
import useContador from "./useContador";

test("incrementa a contagem ao chamar o método incrementar", () => {
  const { result } = renderHook(() => useContador());

  act(() => {
    result.current.incrementar();
  });

  expect(result.current.contador).toBe(1);
});
```

## Do's & Don'ts

### Importância baixa

Erros que não gerarão problemas nos testes, porém, são considerados boas práticas e resultarão em testes mais confiáveis

#### Não utilizar um wrapper

Tornou-se muito habitual utilizar uma variável para salvar o que é retornado por meio do método render(<Component />). Quando não é necessário, basta desestruturá-lo para obter o que precisamos desse método.

Se por algum motivo, ainda assim, quisermos salvar essas informações dentro de uma variável, devemos, por convenção, trocar o seu nome de <b>wrapper</b> para <b>view</b>. Assim, ao usar métodos como render, saberemos que será da view, quando utilizarmos <b>view.render</b>

```js
// ❌
const wrapper = render(<Example prop="1"> /)
wrapper.rerender(<Example prop="2" />)

// ✅
const {rerender} = render(<Example prop="1"> /)
rerender(<Example prop="2" />)
```

#### Múltiplas assertions em uma mesma função waitFor

Vamos supor que precisamos testar algo de forma assíncrona. Se executamos múltiplas assertions dentro do waitFor, mas a função assíncrona falha, deveremos esperar até que ela falhe para visualizar o resultado das demais assertions.

Porém, se separarmos as assertions do waitFor, obteremos os resultados imediatamente, mesmo que o teste assíncrono falhe.

```js
// ❌
await awaitFor(() => {
  expect(window.fetch).toHaveBeenCalledWith("foo");
  expect(window.fetch).toHaveBeenCalledTimes(1);
});

// ✅
await awaitFor(() => expect(window.fetch).toHaveBeenCalledWith("foo"));

expect(window.fetch).toHaveBeenCalledTimes(1);
```

#### Usar métodos get... como assertions

Estabeleceu-se a má prática de usar os métodos get... (getByRole, getByText, etc.) como assertions. Embora isto não gere erro algum, a próxima pessoa que ler o código pode se confundir, pensando que nos esquecemos de remover essa parte do código após uma refatoração.

Então, por convenção e boa prática, continuamos usando o expect junto com os métodos get...

```js
// ❌
screen.getByRole("alert", { name: /error/i });

// ✅
expect(screen.getByRole("alert", { name: /error/i })).toBeInTheDocument();
```

### Importância Média

Bugs podem começar a aparecer e podemos estar realizando algumas tarefas desnecessárias

#### Usar cleanup

Em versões antigas da maior parte das bibliotecas de testing, após cada teste deveríamos fazer uma limpeza do componente que foi montado fazendo uso do <b>cleanup</b>, que era declarado globalmente com a função <b>afterEach(cleanup)</b>.

Hoje em dia, felizmente, a maior parte das bibliotecas realiza essa limpeza de forma automática, tornando o uso do <b>cleanup</b> desnecessário.

```js
// ❌
import { render, screen, cleanup } from "@testing-library/react";
afterEach(cleanup);

// ✅
import { render, screen } from "@testing-library/react";
```

#### Não usar screen

Como vimos no primeiro ponto de baixa importância, o que é retornado por um render(<Component />) geralmente é armazenado em uma variável wrapper e muitas vezes vemos wrapper.getByRole(), ou, no melhor dos casos, o resultado desestruturado: { getByRole } = render(<Component />).

Desde a versão 6.11 da React Testing Library, isso não é preciso. Podemos importar o <b>screen</b> a partir do <b>@testing-library/react</b> tal qual importamos o <b>render</b>

```js
// ❌
const { getByRole } = render(<Example />);
const errorMessageNode = getByRole("alert");

// ✅
render(<Example />);
const errorMessageNode = screen.getByRole("alert");
```

#### Encapsular tudo em act() desnecessariamente

Ao escrever testes em componentes funcionais, quando algo gera um rerender, podemos visualizar uma advertência "When testing, code that causes React state updates should be wrapped into act(...)".

Os testes costumam ser encapsulados em <b>act()</b> para evitar esse warning. Porém, devemos evitar fazê-lo desnecessariamente.

Basta encapsular em <b>act()</b> aquele código que está gerando o rerender. Uma requisição assíncrona, por exemplo.

```js
// ❌
act(() => {
  render(<Example />);
});

const input = screen.getByRole("textbox", { name: /choose a fruit/i });
act(() => {
  fireEvent.keyDown(input, { key: "ArrowDown" });
});

// ✅
render(<Example />);

const input = screen.getByRole("textbox", { name: /choose a fruit/i });
act(() => {
  fireEvent.keyDown(input, { key: "ArrowDown" });
});
```

#### Usar fireEvent no lugar do userEvent

Vale mencionar que, para fazer uso de <b>userEvent</b>, devemos primeiro instalar o pacote <b>@testing-library/user-event</b>, que é um pacote construído sobre o fireEvent, estendendo sua funcionalidade.

A maior diferença entre eles é que o <b>fireEvent</b> somente dispara o evento de alteração, enquanto o <b>userEvent</b> imita com mais precisão o comportamento de um usuário final, detectando, por exemplo, os eventos de </b>keyDown</b>, <b>KeyUp</b> e <b>KeyPress</b> em um input por meio do método <b>type</b>.

```js
// ❌
act(() => {
  render(<Example />);
});

const input = screen.getByRole("textbox", { name: /choose a fruit/i });
act(() => {
  fireEvent.keyDown(input, { key: "ArrowDown" });
});

// ✅
render(<Example />);

const input = screen.getByRole("textbox", { name: /choose a fruit/i });
act(() => {
  fireEvent.keyDown(input, { key: "ArrowDown" });
});
```

### Importância Alta

Provavelmente gerarão problemas com nossos testes ou geraremos testes não-confiáveis

#### Usar assertions incorretos

No exemplo abaixo, acessar o método <b>toBeDisabled()</b> do expect não é o mesmo que acessar a propriedade <b>disabled</b> do botão e então perguntar se seu valor é <b>truthy</b> ou <b>falsy</b>.

Por isso, devemos fazer uso dos assertions corretos, conforme o cenário que precisamos avaliar. O método toBeDisabled() é responsável por verificar se o elemento no DOM está disabled e, caso haja alguam falha, ele imprime um erro via console com mais exatidão.

```js
const button = screen.getByRole("button", { name: /disabled/i });
// ❌
expect(button.disabled).toBe(true); // error message: expect(received).toBe(expected) // Object.is equality
// Expected: true
// Received: false

// ✅
expect(button).toBeDisabled();
// error message: Received element is not disabled: <button />
```

#### Usar a query incorreta

Por exemplo, adicionar desnecessariamente a propriedade <b>data-testid</b> a elementos do DOM ao invés de acessá-lo por seus elementos de acessibilidade, como type de um input.

Assim como essa, existem várias outras formas em que as queries são utilizadas incorretamente. [Doc sobre queries](https://testing-library.com/docs/queries/about/#using-queries)

```js
// ❌
// assuming you've got this DOM to work with:
// <label>Username</label><input data-testid="username" />
screen.getByTestId("username");

// ✅
// change the DOM to be accessible by associating the label and setting the type
// <label for='username'>Username</label><input id='username' type='text' />
screen.getByRole("textbox", { name: /username/i });
```

#### Adicionar elementos de acessibilidade desnecessários

Devemos ter cuidadona hora de adicionar elementos de acessibilidade como aria- ou role para realizar as queries nos testes. Antes de fazê-lo devemos verificar se é realmente necessário. Além da possibilidade de ser desnecessário, podem confundir um software leitor de tela para pessoas com deficiência visual, prejudicando a experiência do usuário.

Uma demonstração de que pode ser desnecessário é adicionar um atributo role a um input. Talvez devêssemos pensar em usar, em seu lugar, o atributo <b>type</b> e acessá-lo usando getByRole('textbox', {name:/inputId/i})

```js
// ❌
render(<button role="button">Click</button>);

// ✅
render(<button>Click</button>);
```

#### Usar variantes de query para tudo

Ao usar <b>queryByRole</b>, <b>queryByText</b> e outras, se o elemento está presente no DOM, conseguiremos receber as informações desejadas. No entanto, não é recomendável usá-lo o tempo todo para outras situações além de verificar se um determinado elemento não está presente.

Ou seja, o único motivo pelo qual usaríamos uma variante de query seria para verificar se um elemento não foi encontrado, pois se não for encontrado ele retornará um null, então podemos cair no "null não está no documento".

Já usando variantes de <b>get</b>, se não encontramos um element, eles devolvem um erro mais detalhado, e inclusive imprimem o documento completo para sabermos o que está acontecendo.

```js
// ❌
expect(screen.queryByRole(''alert)).toBeInTheDocument()

// ✅
expect(screen.getByRole(''alert)).toBeInTheDocument()
expect(screen.queryByRole(''alert)).not.toBeInTheDocument()
```

#### Usar waitFor para esperar elementos que podem ser consultados com variantes de find

Ambos os códigos fazem o mesmo, porém, o segundo é mais simples e a mensagem de erro será melhor detalhada. O mais importante a ser destacado é que as variantes de <b>find</b> já usam <b>waitFor<b> por baixo, tornando o seu uso redundante.

```js
// ❌
const submitButton = await waitFor(() => screen.getByRole("button", { name: /submit/i }));

// ✅
const submitButton = await screen.findByRole("button", { name: /submit/i });
```

#### Passar um callback vazio para o waitFor

Podemos encontrar na internet exemplos de <b>waitFor</b> com callback vazio que pode até funcionar, mas <b>cuidado!</b>.

Podem funcionar porque talvez, tudo o que precisamos esperar seja resolvido na primeira iteração do loop de eventos, mas na verdade o teste está ficando frágil e não-confiável. Por exemplo, se refatorarmos o código assíncrono em algum momento, o teste poderá falhar.

O <b>waitFor</b> espera que algo específico aconteça então não há razão para passar um callback vazio.

```js
// ❌
await waitFor(() => {});
expect(window.fetch).toHaveBeenCaledWith("foo");
expect(window.fetch).toHaveBeenCaledTimes(1);

// ✅
await waitFor(() => {
  expect(window.fetch).toHaveBeenCaledWith("foo");
  expect(window.fetch).toHaveBeenCaledTimes(1);
});
```

#### Provocar efeitos secundários sem um waitFor

<b>waitFor</b> é projetado para coisas sem um tempo específico de resolução. Ele está constantemente avaliando, através de um intervalo ou quando ocorrem alterações no DOM. Por isso, se executamos alguma coisa dentro do <b>waitFor</b>, corremos o risco dessa execução acontecer várias vezes de forma indesejada.

Um exemplo de um rerender do DOM no React. Tudo que está em nosso componente será executado novamente em cada render.

O mesmo acontece dentro do <b>waitFor</b>: tudo que temos dentro dele será executado quntas vezes o <b>waitFor</b> executar o callback.

```js
// ❌
await waitFor(() => {
  fireEvent.keyDown(input, { key: "ArrowDown" });
  expect(screen.getAllByRole("listitem")).toHaveLength(3);
});

// ✅
fireEvent.keyDown(input, { key: "ArrowDown" });
await waitFor(() => {
  expect(screen.getAllByRole("listitem")).toHaveLength(3);
});
```

## Teste em modo watch

```
npm run test src/.../.../component.spec.tsx -- --watch
```
