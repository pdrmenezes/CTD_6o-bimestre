import "./characters.css";
import Character from "./characters.types";

import { charactersApi } from "./characters.endpoints";
import CharactersComponent, { CharactersComponentProps } from "./characters.component";
import CharacterCard from "./Card/characterCard.component";

export { CharactersComponent, charactersApi, CharacterCard };
export type { Character, CharactersComponentProps };
