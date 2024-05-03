import React, { useState } from 'react';
import './styles.css'; 
import bossteam from './assets/bossteam.png';
import axios from 'axios';

const Game = () => {
    // State untuk menyimpan kata start dan end
    const [startWord, setStartWord] = useState("");
    const [endWord, setEndWord] = useState("");
    const [algoChoice, setAlgoChoice] = useState(1);
    const [result, setResult] = useState(null);
    
    // Fungsi untuk menukar start word dan end word
    const handleToggle = () => {
        const temp = startWord;
        setStartWord(endWord);
        setEndWord(temp);
    };

    const handleFindPath = () => {
        axios.post('http://localhost:8080/api/word-azul/find-path', {
            startWord,
            endWord,
            algoChoice
        })
        .then((response) => {
            setResult(response.data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    };

    return (
        <div className="game-container">
            <img src={bossteam} alt="Boss Team" className="top-image" />
            <div className="input-container">
                <input
                    type="text"
                    className="textbox"
                    placeholder="Start Word"
                    value={startWord}
                    onChange={(e) => setStartWord(e.target.value)}
                />
                <button className="toggle-button" onClick={handleToggle}> 
                    ⇆ 
                </button>
                <input
                    type="text"
                    className="textbox"
                    placeholder="End Word"
                    value={endWord}
                    onChange={(e) => setEndWord(e.target.value)}
                />
            </div>
            <div className="centered-container"> 
                <div className="dropdown-container"> 
                    <label htmlFor="algorithm">Choose Algorithm:   </label> 
                    <select id="algorithm" className="dropdown" value={algoChoice} onChange={(e) => setAlgoChoice(Number(e.target.value))}>
                        <option value="1">Uniform-Cost Search</option> 
                        <option value="2">Greedy Best-First Search</option>
                        <option value="3">A*</option>
                    </select>
                </div>
                <button className="search-button" onClick={handleFindPath} >Search</button> 
            </div>
            <div className="results-container"> 
                {result && (
                    <div>
                        <p>Path: {result.path.join(' -> ')}</p>
                        <p>Nodes Visited: {result.nodesVisited}</p>
                        <p>Execution Time: {result.executionTime} ms</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Game;