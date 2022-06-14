import './App.css';
import {useNavigate} from 'react-router-dom'


function App() {
  const navigate = useNavigate();
  const navigateToRandom = () => {
    navigate('/random');
  };

  return (
        <div>
        </div>
  );
}

export default App
