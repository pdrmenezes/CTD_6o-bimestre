import React from "react";
import { rest } from "msw";
import { setupServer } from "msw/node";
// Estamos usando o nosso próprio render e não o da biblioteca
// ao mesmo tempo que exportamos novamente todos os demais da biblioteca
// Assim podemos importar fireEvent e também screen.
import { render, fireEvent, screen } from "../../test-utils";
import App from "../../containers/App";

// Para interceptar requisições, podemos usar uma biblioteca como msw
// durante os testes e retornar “John Smith” após 150 ms
// quando recebemos uma requisição get para o endpoint “api/usuario”

export const handlers = [
  rest.get("/api/user", (req, res, ctx) => {
    return res(ctx.json("John Smith"), ctx.delay(150));
  }),
];

const server = setupServer(...handlers);

// Habilita o mock da API antes dos testes.
beforeAll(() => server.listen());

// Restabelece qualquer requisição em tempo de execução que possa ser adicionada durante os testes.
afterEach(() => server.resetHandlers());

// Desabilita o mock da API após realizar os testes.
afterAll(() => server.close());

test("recupera e recebe um usuário após clicar no botão recuperar usuario", async () => {
  render(<App />);

  // não deve exibir nenhum usuário inicialmente e não deve procurar um usuário
  expect(screen.getByText(/no user/i)).toBeInTheDocument();
  expect(screen.queryByText(/Fetching user\.\.\./i)).not.toBeInTheDocument();

  // depois de clicar no botão 'Buscar usuário', agora deve mostrar que está buscando o usuário.
  fireEvent.click(screen.getByRole("button", { name: /Fetch user/i }));
  expect(screen.getByText(/no user/i)).toBeInTheDocument();

  // após um tempo, o usuário deve ser recebido
  expect(await screen.findByText(/John Smith/i)).toBeInTheDocument();
  expect(screen.queryByText(/Sem usuario/i)).not.toBeInTheDocument();
  expect(screen.queryByText(/Buscando usuario\.\.\./i)).not.toBeInTheDocument();
});
