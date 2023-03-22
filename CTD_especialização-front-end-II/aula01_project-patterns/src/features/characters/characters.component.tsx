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
      {charactersArray.map((iHateThisChars) => (
        <CharacterCard key={iHateThisChars.id} character={iHateThisChars} />
      ))}
    </div>
  );
};

export default CharactersComponent;
