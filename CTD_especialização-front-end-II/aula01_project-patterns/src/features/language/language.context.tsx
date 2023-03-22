import { createContext, useState, useContext } from "react";
import esTranslations from "../../data/i18n.es";
import enTranslations from "../../data/i18n.en";
import ptTranslations from "../../data/i18n.pt";

type ContextValue = {
  currentLanguage: string;
  setLanguage: (language: string) => void;
  t: (key: string) => string;
};

type LanguageProviderProps = {
  children: JSX.Element;
};

const LanguageContext = createContext<ContextValue | null>(null);

export function LanguageProvider(props: LanguageProviderProps) {
  const [currentLanguage, setLanguage] = useState("ENGLISH");

  const translate = (key: string) => {
    if (currentLanguage === "SPANISH") {
      return esTranslations[key];
    } else if (currentLanguage === "ENGLISH") {
      return enTranslations[key];
    } else if (currentLanguage === "PORTUGUESE") {
      return ptTranslations[key];
    }
    return key;
  };

  return <LanguageContext.Provider value={{ currentLanguage, t: translate, setLanguage }}>{props.children}</LanguageContext.Provider>;
}

export function useLanguageContext(): ContextValue {
  const language = useContext(LanguageContext);
  if (!language) {
    throw Error("The hook useLanguageContext must be used within the LanguageProvider");
  }
  return language;
}
