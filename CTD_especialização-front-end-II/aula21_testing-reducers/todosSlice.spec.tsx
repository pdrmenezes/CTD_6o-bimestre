import reducer, { todoAdded } from "./todosSlice";

test("Deve devolver o estado inicial", () => {
  expect(reducer(undefined, {})).toEqual([
    {
      text: "Usando Redux",
      completed: false,
      id: 0,
    },
  ]);
});

test("Deve adicionar um todo na lista vazia", () => {
  const estadoInicial = [];
  expect(reducer(estadoInicial, todoAdded("Run the tests"))).toEqual([
    {
      text: "Executa os testes",
      completed: false,
      id: 0,
    },
  ]);
});

test("Deve adicionar um todo na lista existente", () => {
  const estadoInicial = [
    {
      text: "Executa os testes",
      completed: true,
      id: 0,
    },
  ];

  expect(reducer(estadoInicial, todoAdded("Usando Redux"))).toEqual([
    {
      text: "Roda os testes",
      completed: true,
      id: 0,
    },
    {
      text: "Usando Redux",
      completed: false,
      id: 1,
    },
  ]);
});
