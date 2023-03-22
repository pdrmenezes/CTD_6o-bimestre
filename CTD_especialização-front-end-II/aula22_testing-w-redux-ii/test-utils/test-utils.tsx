import React from "react";
import { render as renderBiblioteca } from "@testing-library/react";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
// Import your own reducer
import userReducer from "../userSlice";

function nossoRender(ui, { preloadedState, store = configureStore({ reducer: { user: userReducer }, preloadedState }), ...renderOptions } = {}) {
  function Wrapper({ children }) {
    return <Provider store={store}>{children}</Provider>;
  }

  return renderBiblioteca(ui, { wrapper: Wrapper, ...renderOptions });
}

// re-export everything
export * from "@testing-library/react";
// override render method
export { nossoRender };
