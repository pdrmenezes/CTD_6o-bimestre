import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { apiUsuario } from "./apiUsuario";

export const buscarUsuario = createAsyncThunk("user/buscarUsuario", async () => {
  const resposta = await apiUsuario.buscarUsuario();
  return resposta.data;
});

const userSlice = createSlice({
  name: "usuario",
  initialState: {
    nome: "Não existe usuário ainda",
    status: "inativo",
  },
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(buscarUsuario.pending, (state, action) => {
      state.status = "carregando";
    });
    builder.addCase(buscarUsuario.fulfilled, (state, action) => {
      state.status = "concluído";
      state.name = action.payload;
    });
  },
});

export const selectUser = (state) => state.usuario.nome;
export const selectUserEstadoBusca = (state) => state.usuario.status;

export default userSlice.reducer;
