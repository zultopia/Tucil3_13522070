import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from "./components/navbar";
import Start from "./components/start";
import Algorithm from "./components/algorithm";
import HowToUsePage from "./components/howtouse";
import AboutMe from "./components/aboutme";
import Game from "./components/game";

function App() {
  return (
    <Router>
      <div className="app-container">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/howtouse/*" element={<HowToUse />} />
          <Route path="/aboutme/*" element={<AboutMePage />} />
          <Route path="/game/*" element={<GamePage />} />
          <Route path="/algorithm/*" element={<AlgorithmPage />} />
        </Routes>
      </div>
    </Router>
  );
}

const Home = () => {
  return (
    <>
      <Navbar />
      <Start />
    </>
  );
};

const AlgorithmPage = () => {
  return (
    <div className="page-container">
      <Navbar />
      <Algorithm />
    </div>
  );
};

const GamePage = () => {
  return (
    <div className="page-container">
      <Navbar />
      <Game />
    </div>
  );
};

const HowToUse = () => {
  return (
    <div className="page-container">
      <Navbar />
      <HowToUsePage />
    </div>
  );
};

const AboutMePage = () => {
  return (
    <div className="page-container">
      <Navbar />
      <AboutMe />
    </div>
  );
};

export default App;