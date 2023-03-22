import { FC } from "react";

type FollowingButtonProps = {
  isFav: boolean;
  onToggleFavorite: (setFav: boolean) => void;
};

const FollowingButtonComponent: FC<FollowingButtonProps> = ({ isFav, onToggleFavorite }: FollowingButtonProps) => {
  const src = isFav ? "/src/assets/star-filled.png" : "/src/assets/star.png";
  const alt = isFav ? "is_favorite" : "is_not_favorite";

  return (
    <div className={"following-button"} onClick={() => onToggleFavorite(!isFav)}>
      <img src={src} alt={alt} />
    </div>
  );
};

export default FollowingButtonComponent;
