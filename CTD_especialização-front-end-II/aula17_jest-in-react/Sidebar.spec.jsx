import { Sidebar } from "./Sidebar";
import { render, screen } from "@testing-library/react";

describe("Sidebar component", () => {
  describe("when the prop visible is true", () => {
    it("shows the content", () => {
      const closeMock = jest.fn();
      render(<Sidebar visible={true} close={closeMock} />);

      const menu1 = screen.getByText("menu 1");
      const menu2 = screen.getByText("menu 2");
      const menu3 = screen.getByText("menu 2");
      expect(menu1).toBeInTheDocument();
      expect(menu2).toBeInTheDocument();
      expect(menu3).toBeInTheDocument();
    });
  });

  describe("when the close button is clicked", () => {
    const closeMock = jest.fn();
    it("calls the close callback", async () => {
      render(<Sidebar visivle={true} close={closeMock} />);
      const closeButton = screen.getByText("X");
      userEvent.click(closeButton);

      await waitFor(() => expect(closeMock).toHaveBeenCalled());
    });
  });

  describe("when the prop visible is false", () => {
    it("does not show the content", () => {
      const closeMock = jest.fn();
      render(<Sidebar visible={false} close={closeMock} />);

      const menu1 = screen.queryByText("menu 1");
      const menu2 = screen.queryByText("menu 2");
      const menu3 = screen.queryByText("menu 2");
      expect(menu1).not.toBeInTheDocument();
      expect(menu2).not.toBeInTheDocument();
      expect(menu3).not.toBeInTheDocument();
    });
  });
});
