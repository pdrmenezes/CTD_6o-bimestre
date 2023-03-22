import { createSlice } from "@reduxjs/toolkit";

const estadoInicial = [
  {
    text: "Usando Redux",
    completed: false,
    id: 0,
  },
];

const todosSlice = createSlice({
  name: "todos",
  estadoInicial,
  reducers: {
    todoAdded(state, action: PayloadAction<string>) {
      state.push({
        id: state.reduce((maxId, todo) => Math.max(todo.id, maxId), -1) + 1,
        completed: false,
        text: action.payload,
      });
    },
  },
});

export const { todoAdded } = todosSlice.actions;
export default todosSlice.reducer;
