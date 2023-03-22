import { FaBars, FaTimes } from "react-icons/fa";
import { SearchBar } from "../../search";
import { FC, useState } from "react";
import { NavLink } from "react-router-dom";
import { useLanguageContext } from "../../language";

const NavMobile: FC = () => {
  const [isOpen, setOpen] = useState<boolean>(false);
  const toggle = () => setOpen((value) => !value);

  const { t } = useLanguageContext();

  return (
    <div className={`container mobile-nav`}>
      <div className={"container"}>
        <NavLink to="/" className={"nav-link"} aria-label="Link to Homepage">
          <img className={"logo"} src={"/src/assets/logo-dh.png"} alt="Digital House Logo" />
        </NavLink>
        <button className={"icon-button"} aria-label="menu-button" onClick={toggle}>
          {isOpen ? <FaTimes /> : <FaBars />}
        </button>
      </div>
      {isOpen && (
        <div className={"container"} style={{ flexDirection: "column" }}>
          <div className={"container"} style={{ width: 400, flexDirection: "column" }}>
            <NavLink to="/" className={"nav-link"} aria-label="Link to Homepage">
              <h3>{t("navigation.home")}</h3>
            </NavLink>
            <NavLink to="/following" className={"nav-link"} aria-label="Link to Following Page">
              <h3>{t("navigation.following")}</h3>
            </NavLink>
          </div>
          <SearchBar />
        </div>
      )}
    </div>
  );
};
export default NavMobile;
