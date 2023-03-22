import { rest } from "msw";
import { setupServer } from "msw/node";
import { render } from "@testing-library/react";
import App from "./App";
import { Provider } from "Context";

describe("App", () => {
  describe("when everything goes well", () => {
    const server = setupServer(
      rest.get("https://randomuser.me/api", (req, res, ctx) => {
        return res(
          ctx.delay(1200),
          ctx.status(200),
          ctx.json({
            results: [
              {
                name: { first: "Steve", last: "Jobs" },
                email: "steve@jobs.com",
                picture: {
                  large: "https://randomuser.me/api/portraits/men/68.jpg",
                },
              },
            ],
          })
        );
      })
    );

    // Establish API mocking before all tests
    beforeAll(() => server.listen());
    // Reset any request handlers that we may add so they don't affect other tests
    afterEach(() => server.resetHandlers());
    // Clean up after tests are finished
    afterAll(() => server.close());

    it("shows the loading message", async () => {
      render(
        <Provider store={store}>
          <App />
        </Provider>
      );
      expect(await screen.findByText("loading user data")).toBeInTheDocument();
    });
    it("shows user's picture, name and email", () => {
      render(
        <Provider store={store}>
          <App />
        </Provider>
      );
      const image = await screen.findByAltText("Steve");
      expect(image).toBeInTheDocument();
      expect(image).toHaveAttribute("src", "https://randomuser.me/api/portraits/men/68.jpg");

      expect(await screen.findByText("Steve Jobs")).toBeInTheDocument();
      expect(await screen.findByText("steve@jobs.com")).toBeInTheDocument();
    });
    it("changes loading message to 'success'", () => {
      expect(await screen.findByText("success").toBeInTheDocument());
    });
  });
  describe("when something goes wrong", () => {
    const server = setupServer(
      rest.get("https://randomuser.me/api", (req, res, ctx) => {
        return res(
          ctx.status(500),
          ctx.json({
            error: "something went wrong",
          })
        );
      })
    );

    beforeAll(() => server.listen());
    afterEach(() => server.resetHandlers());
    afterAll(() => server.close());

    it("changes the loading message to 'error'", () => {
      expect(await screen.findByText("error")).toBeInTheDocument();
    });
    it("renders the error explanation 'error fetching user data'", () => {
      expect(await screen.findByText("Error fetching user data")).toBeInTheDocument();
    });
  });
});
