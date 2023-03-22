import { FaSearch } from "react-icons/fa";
import { ChangeEvent, FC } from "react";
import { useAppDispatch, useAppSelector } from "../../store/hooks";
import { searchLocations } from "../locations/locations.slices";
import { useNavigate } from "react-router-dom";

const SearchBar: FC = () => {
  const locationsQuery = useAppSelector((state) => state.locations.query);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const onSearch = (e: ChangeEvent<HTMLInputElement>) => {
    dispatch(searchLocations(e.target.value));
    navigate("/");
  };

  return (
    <form aria-label="form" onSubmit={(e) => e.preventDefault()} className={"search-form"}>
      <input className={"search-input"} aria-label="input" placeholder="Search..." value={locationsQuery} onChange={onSearch} />
      <button className={"search-button"} type="button" aria-label="Search Button">
        <FaSearch />
      </button>
    </form>
  );
};

export default SearchBar;
