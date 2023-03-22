import CardPicture from "./CardPicture";
import CardContent from "./CardContent";
import CardActions from "./CardActions";
import Character from "../characters.types";

export default function CharacterCard({ character }: { character: Character }) {
  return (
    <div className="card">
      <div className={"card-image"}>
        <CardPicture props={character.image} />
      </div>
      <div className={"card-body"}>
        <CardContent props={character.name} />
        <CardActions character={character} />
      </div>
    </div>
  );
}
