import React from "react";
import Character from "../character";
import withAuthenticatedUser from "../../withAuthenticatedUser";

function CharactersList({ changeState }) {
  return (
    <div>
      <h1>Orkut</h1>
      <button onClick={changeState}>Sair</button>
      <Character />
    </div>
  );
}

export default withAuthenticatedUser(CharactersList);
