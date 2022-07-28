import './App.css';
import {useLocalState} from "./util/useLocalStorage"
import {useEffect} from "react";

function App() {
    const [jwt, setJwt] = useLocalState("","jwt")

    // useEffect(() =>{
    // }, [jwt]);
  return (
        <div>
        </div>

  );
}
export default App
