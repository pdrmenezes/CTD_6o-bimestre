O redux-thunk é construído usando algo conhecido como currying, que nada mais é
do que um processo de decomposição de uma função que toma mais de um parâmetro em
um conjunto de funções invocadas fazendo uso desses mesmos parâmetros.

// Função de adição sem curry, que toma três parâmetros

```js
function adicao(a, b, c) {
  return a + b + c;
}
console.log(adicao(1, 2, 3)); // 6
// Neste exemplo realizamos curry
// chamando a função três vezes
// com um argumento por vez
function adicaoCurrying(a) {
  return (b) => {
    return (c) => {
      return a + b + c;
    };
  };
}
console.log(adicaoCurrying(1)(2)(3)); // 6
```

Para o caso do código anterior - ou seja, do thunk - seria algo como:

```js
const thunk = (store) => {
  const { dispatch, getState } = store;
  return (next) => {
    return (action) => {
      if (typeof action === "function") {
        return action(dispatch, getState);
      }
      return next(action);
    };
  };
};
```

E ele é invocado assim:

```js
const invocarThunk = (action) => thunk(store)(next)(action);
```

Bem, agora que já entendemos como o middleware pode ser construído, precisamos realizar
um mock para cada uma destas funções: getState, dispatch e next.

Para isso, usaremos jest.fn().

```js
const create = () => {
  const store = {
    getState: jest.fn(() => ({})),
    dispatch: jest.fn(),
  };
  const next = jest.fn();
  const invoke = (action) => thunk(store)(next)(action);
  return { store, next, invoke };
};
```

E escrevemos os nossos testes:

```js
test("passa por uma ação que não é uma função", () => {
  const { next, invoke } = create();
  const action = { type: "TEST" };
  invoke(action);
  expect(next).toHaveBeenCalledWith(action);
});

test("chama a função", () => {
  const { invoke } = create();
  const fn = jest.fn();
  invoke(fn);
  expect(fn).toHaveBeenCalled();
});
test("passa dispatch e getState", () => {
  const { store, invoke } = create();
  invoke((dispatch, getState) => {
    dispatch("TEST DISPATCH");
    getState();
  });
  expect(store.dispatch).toHaveBeenCalledWith("TEST DISPATCH");
  expect(store.getState).toHaveBeenCalled();
});
```

Algo um pouco difícil de se seguir, não é? É por isso que, na documentação do Redux,
recomenda-se realizar testes de integração e não testes de unidade dos thunks.

É mais fácil testar um componente que utiliza thunk e verificar os resultados durante a sua
renderização do que testar o thunk diretamente. Assim, se o nosso componente funcionar
corretamente, poderemos afirmar que a lógica do Redux estará também funcionando como
deveria.

## Testing Actions

Quando escrevemos testes para ações, a documentação do Redux indica que, se estamos usando ferramentas como redux-toolkit, é altamente recomendado não escrever ações individuais, mas sim nos apoiar nos builders e nos slices para esse tipo de ações, evitando assim escrever esses testes. Os criadores de redux-toolkit já os escreveram por nós.

Se, em vez disso, decidirmos escrever testes por cada ação realizada em uma determinada parte da nossa aplicação, cairemos novamente nos testes de unidade (em lugar de realizar testes de integração). Isso sem considerar a quantidade de código que deveríamos manter nos testes que ficariam acoplados nas implementações do nosso código (consideremos que, nesses casos, é recomendado realizar testes de unidade diretamente sobre o slice, que contém as ações dentro dele).

Se por algum motivo optamos por escrever as ações manualmente, assim como também os testes destas (que, ao ser uma função pura, recebem algo e devolvem algo), podemos escrever algo assim:

```js
const dataForTest = {
  actionString: "addToCart",
  actionData: {
    product: "course",
    title: "Redux Testing",
  },
};

const actionMock = jest.fn(({ actionString, actionData }) => ({
  type: actionString,
  actionData,
}));

describe("testando uma ação ", () => {
  it("deveria retornar ", () => {
    actionMock(dataForTest);
    expect(actionMock).toHaveBeenCalledWith(dataForTest);
  });
});
```
