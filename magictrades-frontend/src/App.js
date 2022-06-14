
import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect} from 'react';
import RandomCard from "./components/RandomCard";
import Home from "./components/Home";
import {BrowserRouter as Router, Route, Routes, useNavigate} from 'react-router-dom'


function App() {
  const navigate = useNavigate();
  const navigateToRandom = () => {
    navigate('/random');
  };

  return (
        <div>
        <button onClick={navigateToRandom}>RandomCard</button>
        <hr />
        <Routes>
          <Route path="/random" element={<RandomCard/>} />
        </Routes>
        </div>

  );
}

export default App;
