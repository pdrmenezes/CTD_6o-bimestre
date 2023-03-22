import axios from "axios";

jest.mock("axios");

const mockedAxios = axios as jest.Mocked<typeof axios>;

const fakeUsers = [
  { id: 1, user: "Steve", username: "stevetrabalhos" },
  { id: 2, user: "Fernanda", username: "fernandatrabalhos" },
];

mockedAxios.get.mockResolvedValue({ data: fakeUsers });

beforeEach(() => {
  mockedAxios.get.mockResolvedValue({ data: fakeUsers });
  render(<App />);
});

describe("App", () => {
  describe("Quando o carregamento terminar", async () => {
    test("Mostra a mensagem de carregamento", async () => {
      const loadingMessage = screen.queryByText("Carregando usuarios...");
      expect(loadingMessage).toBeInTheDocument();
    });
  });
  test("Não deve mostrar o carregamento", async () => {
    await waitFor(() => {
      const loadingMessage = screen.queryByText("Carregando usuarios...");
      expect(loadingMessage).not.toBeInTheDocument();
    });
  });
  test("Exibir o nome dos usuários", async () => {
    await waitFor(() => {
      expect(screen.queryByText("Steve")).toBeInTheDocument();
      expect(screen.queryByText("Fernanda")).toBeInTheDocument();
    });
  });
});
test("Exibir o @ dos usuários", async () => {
  await waitFor(() => {
    expect(screen.queryByText("@stevetrabalhos")).toBeInTheDocument();
    expect(screen.queryByText("@fernandatrabalhos")).toBeInTheDocument();
  });
});
