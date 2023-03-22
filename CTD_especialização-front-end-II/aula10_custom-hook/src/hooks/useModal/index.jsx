import { useState } from "react"

export function useModal(){
  const [isShowing, setIsShowing] = useState(false);
  
  function toggle(){
    setIsShowing((prevState) => !prevState)
  }
  return [isShowing, toggle];
}
