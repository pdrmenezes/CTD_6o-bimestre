import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { buscarUsuario, selectUser, selectUserFetchStatus } from "./userSlice";

export default function App() {
  const dispatch = useDispatch();
  const usuario = useSelector(selectUser);
  const estadoRequisicao = useSelector(selectUserFetchStatus);

  return (
    <div>
      {/* Exibe o nome do usuário atual */}
      <div>{usuario}</div>
      {/* Ao clicar, ele dispara o thunk de busca de usuário */}
      <button onClick={() => dispatch(buscarUsuario())}>Buscar usuário</button>
      {/* Exibe na UI o estado da requisição */}
      {estadoRequisicao === "carregando" && <div>Buscando usuário...</div>}
    </div>
  );
}
