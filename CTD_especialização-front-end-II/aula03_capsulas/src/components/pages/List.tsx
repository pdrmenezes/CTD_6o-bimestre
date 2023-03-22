import { ReactElement, useEffect, useRef, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../state/hooks";
import { Character } from "../character";
import axios from "axios";
import { ICharacter } from "../../types";

export const api = axios.create({
  baseURL: "https://rickandmortyapi.com/api/character/",
});

export function List(): ReactElement {
  const [characterSearched, setCharacterSearched] = useState<ICharacter>();
  const [error, setError] = useState("");
  const [inputId, setInputId] = useState("");

  const fetchCharactersById = async (inputId: string) => {
    try {
      const { data } = await api.get(`${inputId}`);
      setCharacterSearched(data);
    } catch (error) {}
  };

  useEffect(() => {
    fetchCharactersById(inputId);
  }, [inputId]);

  return (
    <>
      <h1>CHARACTERS LIST</h1>
      <div id="list">
        <input type="number" onChange={(e) => setInputId(e.target.value)} />
        {characterSearched ? <Character key={characterSearched.id} character={characterSearched} /> : <p>{error}</p>}
      </div>
    </>
  );
}
