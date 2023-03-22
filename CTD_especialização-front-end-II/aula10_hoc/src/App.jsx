import React, { useState } from "react";
import CharactersList from "./components/characters_list";

import "./styles.css";

const App = () => {
  const [logged, setLogged] = useState(false);

  const changeState = () => {
    setLogged(!logged);
  };

  return <CharactersList logged={logged} changeState={changeState} />;
};

export default App;
