import { useAppDispatch, useAppSelector } from "../../../store/hooks";
import { FollowingButtonComponent } from "../../following/button";
import { addCharacterToFollowingList, removeCharacterToFollowingList } from "../../following/following.slices";
import Character from "../characters.types";

export default function CardActions({ character }: { character: Character }) {
  const followingIds = useAppSelector((state) => state.following.followingIds);
  const dispatch = useAppDispatch();

  const onToggleFavorite = (character: Character, setFav: boolean) => {
    if (setFav) {
      dispatch(addCharacterToFollowingList(character.id));
    } else {
      dispatch(removeCharacterToFollowingList(character.id));
    }
  };

  return (
    <FollowingButtonComponent isFav={followingIds.indexOf(character.id) >= 0} onToggleFavorite={(setFav) => onToggleFavorite(character, setFav)} />
  );
}
