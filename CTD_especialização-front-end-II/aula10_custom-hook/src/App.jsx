import React from "react";
import Modal from "./modal";
import Character from "./components/Character/index.tsx";
import "./styles.css";
import { useModal } from "./hooks/useModal";

const App = () => {
  const [isShowing, toggle] = useModal();
  console.log(isShowing);

  return (
    <div className="App">
      <img src="logo.svg" alt="logo rick and morty" />
      <div>
        <button onClick={() => toggle()}>Rick's vivos</button>
        <button onClick={() => toggle()}>Rick's mortos</button>
      </div>

      <Modal
        isShowing={isShowing}
        hide={() => (isShowing ? toggle(isShowing) : null)}
        title="Rick's vivos"
      >
        <Character status="alive" />
      </Modal>

      <Modal
        isShowing={isShowing}
        hide={() => (isShowing ? toggle(isShowing) : null)}
        title="Rick's mortos"
      >
        <Character status="dead" />
      </Modal>
    </div>
  );
};

export default App;
