import { useState } from "react";

type UseToggleReturn = {
  isOpen: boolean;
  toggleFn: () => void;
};

export default function useToggle(initialState: boolean): UseToggleReturn {
  const [isOpen, setIsOpen] = useState(initialState);

  function toggleFn() {
    setIsOpen(!isOpen);
  }
  return { toggleFn, isOpen };
}

function Modal() {
  const { isOpen, toggleFn } = useToggle(false);
}
