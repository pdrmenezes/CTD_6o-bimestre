// based on bootsrap's input group
import { ReactElement } from "react";

export function CompoundInput({ children }: { children: [ReactElement, ReactElement] }) {
  return (
    <div style={{ display: "flex" }}>
      <div>{children[0] ?? null}</div>
      <input />
      <div>{children[1] ?? null}</div>
    </div>
  );
}

export function Left({ children }) {
  return children;
}
export function Right({ children }) {
  return children;
}

export default function App() {
  <CompoundInput>
    <Left>Ixqueerda</Left>
    <Right>Direeeeita</Right>
  </CompoundInput>;
}
