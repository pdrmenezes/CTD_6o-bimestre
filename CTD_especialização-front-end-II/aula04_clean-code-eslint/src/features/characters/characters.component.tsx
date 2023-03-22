import { FC } from "react";
import { useGetCharactersQuery } from "./characters.endpoints";
import CharacterCard from "./Card/characterCard.component";

export type CharactersComponentProps = {
  rickIDDS: number[];
};

const CharactersComponent: FC<CharactersComponentProps> = ({ rickIDDS }: CharactersComponentProps) => {
  const { data: characters, error, isLoading } = useGetCharactersQuery({ ids: rickIDDS });

  if (isLoading) return <div>Loading characters...</div>;
  if (error || !characters) return <div>Error when loading. Please try again later.</div>;
  const charactersArray = Array.isArray(characters) ? characters : [characters];

  return (
    <div className={"characters"}>
      {charactersArray.map((character) => (
        <CharacterCard key={character.id} character={character} />
      ))}
    </div>
  );
};

export default CharactersComponent;
