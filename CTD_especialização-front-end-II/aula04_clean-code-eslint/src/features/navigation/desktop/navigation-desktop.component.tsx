import { SearchBar } from "../../search";
import { FC } from "react";
import { NavLink } from "react-router-dom";
import { useLanguageContext } from "../../language";

const NavDesktop: FC = () => {
  const { t } = useLanguageContext();

  return (
    <div className={"container nav"} style={{ justifyContent: "space-between" }}>
      <div className={"container"} style={{ width: 400 }}>
        <NavLink to="/" className={"nav-link"}>
          <img className={"logo"} src={"/src/assets/logo-dh.png"} alt="Digital House Logo" />
        </NavLink>
        <NavLink to="/" className={"nav-link"}>
          <h3>{t("navigation.home")}</h3>
        </NavLink>
        <NavLink to="/following" className={"nav-link"}>
          <h3>{t("navigation.following")}</h3>
        </NavLink>
      </div>
      <SearchBar />
    </div>
  );
};

export default NavDesktop;
