import { FC } from "react";
import { useLanguageContext } from "./language.context";

const LanguageComponent: FC = () => {
  const { setLanguage, currentLanguage, t } = useLanguageContext();

  return (
    <div className={"language"}>
      <div onClick={() => setLanguage("SPANISH")} className={currentLanguage === "SPANISH" ? "language-button active" : "language-button"}>
        {t("language.spanish")}
      </div>
      <button onClick={() => setLanguage("ENGLISH")} className={currentLanguage === "ENGLISH" ? "language-button active" : "language-button"}>
        {t("language.english")}
      </button>
      <button onClick={() => setLanguage("PORTUGUESE")} className={currentLanguage === "PORTUGUESE" ? "language-button active" : "language-button"}>
        {t("language.portuguese")}
      </button>
    </div>
  );
};
export default LanguageComponent;
