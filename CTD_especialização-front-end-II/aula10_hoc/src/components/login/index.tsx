import React from "react";

const Login = ({ changeState }) => {
  return (
    <>
      <h1>Entrar no Orkut</h1>
      <button onClick={changeState}>Entrar</button>
    </>
  );
};
export default Login;
