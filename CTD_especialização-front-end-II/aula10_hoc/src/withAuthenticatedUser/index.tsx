import Login from "../components/login";

export default function withAuthenticatedUser(Component) {
  return function wrapper(props) {
    if (props.logged) {
      console.log("AQUIII LOGADO");
      return <Component {...props} />;
    }

    console.log("AQUIII DESLOGADO");

    return <Login {...props} />;
  };
}
